package com.controller;

import com.service.MenuService;
import com.service.UserService;
import com.utils.ResponseResult;
import com.vo.params.UserParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <h4>blog_admin</h4>
 * <p>用户信息相关模块</p>
 *
 * @author : zlz
 * @date : 2022-08-18 19:39
 **/
@Api(tags = "用户信息模块")
@RestController
@RequestMapping("api")
public class UserInfoController {
    /**
     * 完善用户信息模块
     */
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "完善/更新用户信息",notes = "参数必填")
    @ApiImplicitParam(name = "userParams",value = "需要完善或者更新的信息")
    @PostMapping("user/fill/info")
    public ResponseResult FillUserInfo(@RequestBody UserParams userParams){
        return userService.FillUserInfo(userParams);
    }
    /**
     * 用户详情模块
     */
    @ApiOperation(value = "获取用户详情")
    @GetMapping("/user/info")
    private ResponseResult getUserInfo(){
        return userService.getUserInfo();
    }
//   TODO获取用户权限
    /**
     * 查询用户权限信息
     */
    @ApiOperation(value = "获取用户权限",tags = "获取当前用户权限")
    @GetMapping("/user/perms")
    private ResponseResult getUserPerms(){
        return menuService.getUserPerms();
    }
}
