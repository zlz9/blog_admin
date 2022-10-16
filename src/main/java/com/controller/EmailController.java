package com.controller;

import com.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <h4>blog_admin</h4>
 * <p>发送邮箱验证</p>
 *
 * @author : zlz
 * @date : 2022-10-03 08:07
 **/
@RestController
@RequestMapping("api")
public class EmailController {
    @Resource
    private JavaMailSender javaMailSender;
    @Autowired
    private RedisCache redisCache;

    //读取yml文件中username的值并赋值给form
    @Value("${spring.mail.username}")
    private String from;

    @GetMapping("sendEmail")
    public String sendSimpleMail(@RequestParam(value = "emailReceiver") String emailReceiver) {
        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件发送者
        message.setFrom(from);
        // 设置邮件接收者
        message.setTo(emailReceiver);
        // 设置邮件的主题
        message.setSubject("登录验证码");
        // 设置邮件的正文
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int r = random.nextInt(10);
            code.append(r);
        }
        /**
         * 把code存入redis
         */
        redisCache.setCacheObject("code", code, 5, TimeUnit.MINUTES);
        String text = "您的验证码为：" + code + ",请勿泄露给他人。";
        message.setText(text);
        // 发送邮件
        try {
            javaMailSender.send(message);
            return "发送成功";
        } catch (MailException e) {
            e.printStackTrace();
        }
        return "发送失败";
    }
}