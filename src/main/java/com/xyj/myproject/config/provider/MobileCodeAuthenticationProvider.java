package com.xyj.myproject.config.provider;

import com.xyj.myproject.config.token.MobileCodeAuthenticationToken;
import com.xyj.myproject.entity.SysUser;
import com.xyj.myproject.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import redis.clients.jedis.Jedis;

import java.util.HashSet;
import java.util.Set;

public class MobileCodeAuthenticationProvider implements AuthenticationProvider {

    private static Jedis jedis = new Jedis("localhost", 6379);

    @Autowired
    private SysUserService sysUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String mobile = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
        String code = (String) authentication.getCredentials();
        if (StringUtils.isEmpty(mobile)){
            throw new InternalAuthenticationServiceException("手机号为空");
        }
        if (StringUtils.isEmpty(code)){
            throw new InternalAuthenticationServiceException("验证码为空");
        }
        //根据手机号查找用户,判断是否已经注册
        SysUser user = sysUserService.selectByPhoneNumb(mobile);
        if (user == null){
            //当用户未注册时返回
            throw new InternalAuthenticationServiceException("用户未注册");
        }
        //获取验证码
        String redisCode = jedis.get("login_code_" + mobile);
        if (StringUtils.isEmpty(redisCode)){
            //当获取不到验证码时返回验证码过期,请用户重新发送
            throw new BadCredentialsException("验证码已过期");
        }
        if (!code.equals(redisCode)) {
            //当验证码与redis中的验证码不匹配时返回
            throw new BadCredentialsException("验证码不正确");
        }
        MobileCodeAuthenticationToken result = new MobileCodeAuthenticationToken(user.getUserName(),
                listUserGrantedAuthorities(mobile));
        result.setDetails(authentication.getDetails());

        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (MobileCodeAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private Set<GrantedAuthority> listUserGrantedAuthorities(String username) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        if (StringUtils.isEmpty(username)) {
            return authorities;
        }
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }


}
