package com.exception;

import javax.naming.AuthenticationException;

/**
 * <h4>blog_admin</h4>
 * <p></p>
 *
 * @author : zlz
 * @date : 2022-08-27 00:48
 **/
public class CaptchaException extends AuthenticationException {
    public CaptchaException(String msg){
        super(msg);
    }
}
