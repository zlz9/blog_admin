package com.handler;

import com.alibaba.fastjson.JSON;
import com.utils.ResponseResult;
import com.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h4>blog_admin</h4>
 * <p>验证码登录异常</p>
 *
 * @author : zlz
 * @date : 2022-11-09 12:51
 **/
public class CapterFailureHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult<Object> result = new ResponseResult<>(555, "验证码错误");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
