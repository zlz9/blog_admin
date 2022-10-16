package com.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.utils.RedisCache;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h4>blog_admin</h4>
 * <p></p>
 *
 * @author : zlz
 * @date : 2022-08-26 21:25
 **/
@RequestMapping("api")
@RestController
public class OAuth {
    @Autowired
    private RedisCache redisCache;

    /**
     * 验证码
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "验证码",notes = "获取验证码，请求请带上时间戳，避免缓存")
    @GetMapping( "/captcha")
    public void getCaptcha(HttpServletResponse response) throws IOException {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116, 30, 4, 10);
        String code = lineCaptcha.getCode();
        redisCache.setCacheObject("captcha", code);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            lineCaptcha.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
