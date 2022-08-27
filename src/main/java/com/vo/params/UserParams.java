package com.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <h4>blog_admin</h4>
 * <p>填写用户信息模块</p>
 *
 * @author : zlz
 * @date : 2022-08-18 19:36
 **/
@Data
@ApiModel(description = "实体类", value = "更新用户信息参数")
public class UserParams {

//    private Long id;
@ApiModelProperty(name = "nickName",value = "昵称",example = "小明",required = true)
    private String nickName;
    @ApiModelProperty(name = "sex",value = "性别",example = "男",required = true)
    private String sex;
    @ApiModelProperty(name = "email",value = "email",example = "邮箱",required = true)
    private String email;
    @ApiModelProperty(name = "age",value = "年龄",example = "20",required = true)
    private Integer age;
    @ApiModelProperty(name = "avator",value = "头像",example = "http:xxxx.xxx.xxx",required = true)
    private String avator;
    @ApiModelProperty(name = "phoneNumber",value = "手机号",example = "1838888813",required = true)
    private Integer phoneNumber;
}
