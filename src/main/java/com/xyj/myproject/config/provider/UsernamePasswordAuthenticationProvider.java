package com.xyj.myproject.config.provider;

import com.xyj.myproject.config.WebSecurityConfig;
import com.xyj.myproject.entity.SysPermission;
import com.xyj.myproject.entity.SysUser;
import com.xyj.myproject.service.SysPermissionService;
import com.xyj.myproject.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
        String password = (String) authentication.getCredentials();
        if (StringUtils.isEmpty(username)){
            throw new InternalAuthenticationServiceException("用户名为空");
        }
        if (StringUtils.isEmpty(password)){
            throw new InternalAuthenticationServiceException("密码为空");
        }
        //根据用户名查找用户
        SysUser user = sysUserService.selectByName(username);
        // 认证用户名
        if (user == null) {
            throw new InternalAuthenticationServiceException("用户不存在");
        }
        // 认证密码
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(username,
                authentication.getCredentials(), listUserGrantedAuthorities(username));
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private Set<GrantedAuthority> listUserGrantedAuthorities(String username) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        if (StringUtils.isEmpty(username)) {
            return authorities;
        }
        //根据用户名查询用户
        SysUser sysUser = sysUserService.selectByName(username);
        if (sysUser != null) {
            //获取该用户所拥有的权限
            List<SysPermission> sysPermissions = sysPermissionService.selectListByUser(sysUser.getId());
            // 声明用户授权
            sysPermissions.forEach(sysPermission -> {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysPermission.getPermissionCode());
                authorities.add(grantedAuthority);
            });
        }

        return authorities;
    }

}
