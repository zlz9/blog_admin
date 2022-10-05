package com;

import com.utils.UserNameUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class BlogAdminApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 测试密码加密解密
     */
    @Test
    public void Encode() {
        String encode = passwordEncoder.encode("chl");
        System.out.println(encode);
    }
    @Test
    public void RawPassword(){
        boolean matches = passwordEncoder.matches("admin",
                "$2a$10$S91FP7Ygg8Dh9ODE3vSbQ.MTklyzYrLZZ.vaWwVD3XrVfwql2f9sS");
        System.out.println(matches);
    }
    /**
     * 测试生成用户名
     */
    @Test
    public void CreateUserName(){
        String randomJianHan = UserNameUtils.getRandomJianHan(4);
        System.out.println("中文"+randomJianHan);
    }
    public void CreateUserName2(){
        String s = UserNameUtils.getStringRandom(4);
        System.out.println("英文"+s);
    }
}
