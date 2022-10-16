package com.vo.params;

import lombok.Data;

/**
 * <h4>blog_admin</h4>
 * <p>用户注册参数</p>
 *
 * @author : zlz
 * @date : 2022-08-18 19:14
 **/
@Data
public class RegisterParams {
     private String UserName;
     private String email;
     private String code;
     private String password;
}
