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
    private String userName;
//    @ApiModelProperty(name = "id", value = "用户id", required = true)
//    @ApiModelProperty(name = "nickName", value = "昵称", example = "小明", required = true)
    private String nickName;
//    @ApiModelProperty(name = "sex", value = "性别", example = "男", required = true)
    private String sex;
//    @ApiModelProperty(name = "email", value = "email", example = "邮箱", required = true)
    private String email;
//    @ApiModelProperty(name = "age", value = "年龄", example = "20", required = true)
    private Integer age;
//    @ApiModelProperty(name = "avator", value = "头像", example = "http:xxxx.xxx.xxx", required = true)
    private String avator;
//    @ApiModelProperty(name = "phoneNumber", value = "手机号", example = "1838888813", required = true)
    private Integer phone;
//    @ApiModelProperty(name = "motto", value = "座右铭", example = "今天是美好的一天", required = true)
    private String motto;
//    @ApiModelProperty(name = "birthday", value = "出生日期", example = "213132131", required = true)
    private Long birthday;
}
