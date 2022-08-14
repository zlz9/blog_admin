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
        String encode = passwordEncoder.encode("admin123");
        System.out.println(encode);
    }
    @Test
    public void RawPassword(){
        boolean matches = passwordEncoder.matches("admin123",
                "$2a$10$z0Gx5uGzsIme8293SOAOdOPwCEFyAPs2clCYjTKwAiq5MvxcBA/y6");
        System.out.println(matches);
    }
}
