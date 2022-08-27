package com.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <h4>blog_admin</h4>
 * <p>用户注册参数</p>
 *
 * @author : zlz
 * @date : 2022-08-18 19:14
 **/
@Data
@ApiModel(description = "实体类", value = "用户注册参数")
public class RegisterParams {
     @ApiModelProperty(name = "userName",value = "用户名",example = "小明",required = true)
     private String userName;
     @ApiModelProperty(name = "password",value = "密码",example = "1234",required = true)
     private String password;
}
