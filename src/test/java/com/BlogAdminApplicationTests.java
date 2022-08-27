package com;

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

    @Test
    public void Encode() {
        String encode = passwordEncoder.encode("admin");
        System.out.println(encode);
    }
    @Test
    public void RawPassword(){
        boolean matches = passwordEncoder.matches("admin",
                "$2a$10$S91FP7Ygg8Dh9ODE3vSbQ.MTklyzYrLZZ.vaWwVD3XrVfwql2f9sS");
        System.out.println(matches);
    }
}
