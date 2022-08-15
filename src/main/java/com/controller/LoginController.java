package com.controller;

import com.domain.User;
import com.service.LoginService;
import com.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h4>blog_admin</h4>
 * <p>登录模块</p>
 *
 * @author : zlz
 * @date : 2022-08-13 11:04
 **/
@Api(tags = "用户登录注册模块")
@RestController
@RequestMapping("api")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "用户登录", notes = "用户名,密码必填。其他不填")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, paramType = "form"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "form")
    })
    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        return loginService.login(user);
    }

    @ApiOperation(value = "退出登录", notes = "直接退出,需要请求头携带token")
    @PostMapping("/user/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }
}
