package com.xyj.myproject.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author: XuYinjie
 * @Description:
 * @Date Create in 2023/9/3 20:50
 */
public class NotLoginException extends AuthenticationException {

    public NotLoginException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotLoginException(String msg) {
        super(msg);
    }
}
