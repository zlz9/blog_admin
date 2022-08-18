package com.vo.params;

import lombok.Data;

/**
 * <h4>blog_admin</h4>
 * <p>填写用户信息模块</p>
 *
 * @author : zlz
 * @date : 2022-08-18 19:36
 **/
@Data
public class UserParams {
    private Long id;
    private String nickName;
    private String sex;
    private String email;
    private Integer age;
    private String avator;
    private Integer phoneNumber;

}
