package com.xyj.myproject.config.handler;

import com.alibaba.fastjson.JSON;
import com.xyj.myproject.common.entity.JsonResult;
import com.xyj.myproject.common.enums.ResultCode;
import com.xyj.myproject.common.utils.ResultTool;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Hutengfei
 * @Description: 登录失败处理逻辑
 * @Date Create in 2019/9/3 15:52
 */
@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException{
        //返回json数据
        JsonResult result = null;
        if (e instanceof AccountExpiredException) {
            //账号过期
            result = ResultTool.fail(ResultCode.USER_ACCOUNT_EXPIRED);
        } else if (e instanceof BadCredentialsException) {
            //密码错误
            result = ResultTool.fail(ResultCode.USER_CREDENTIALS_ERROR);
            //根据错误中的message判断错误类型
            if (e.getMessage().equals("验证码已过期")){
                result = ResultTool.fail(ResultCode.CODE_EXPIRED);
            }
            if (e.getMessage().equals("验证码不正确")){
                result = ResultTool.fail(ResultCode.CODE_ERROR);
            }
        } else if (e instanceof CredentialsExpiredException) {
            //密码过期
            result = ResultTool.fail(ResultCode.USER_CREDENTIALS_EXPIRED);
        } else if (e instanceof DisabledException) {
            //账号不可用
            result = ResultTool.fail(ResultCode.USER_ACCOUNT_DISABLE);
        } else if (e instanceof LockedException) {
            //账号锁定
            result = ResultTool.fail(ResultCode.USER_ACCOUNT_LOCKED);
        } else if (e instanceof InternalAuthenticationServiceException) {
            //用户不存在
            result = ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
            //根据错误中的message判断错误类型
            if (e.getMessage().equals("手机号为空")){
                result = ResultTool.fail(ResultCode.MOBILE_IS_EMPTY);
            }
            if (e.getMessage().equals("验证码为空")){
                result = ResultTool.fail(ResultCode.CODE_IS_EMPTY);
            }
            if (e.getMessage().equals("用户未注册")){
                result = ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
            }
            if (e.getMessage().equals("用户名为空")){
                result = ResultTool.fail(ResultCode.USERNAME_IS_EMPTY);
            }
            if (e.getMessage().equals("密码为空")){
                result = ResultTool.fail(ResultCode.PASSWORD_IS_EMPTY);
            }
        }else{
            //其他错误
            result = ResultTool.fail(ResultCode.COMMON_FAIL);
        }
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
