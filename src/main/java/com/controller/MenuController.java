package com.controller;

import com.service.MenuService;
import com.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h4>blog_admin</h4>
 * <p>权限信息</p>
 *
 * @author : zlz
 * @date : 2022-08-19 20:57
 **/
@Api(tags = "权限列表模块")
@RestController
@RequestMapping("api")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @ApiOperation(value = "获取用户权限表",notes = "获取当前登录用户的权限表")
    @GetMapping("user/menu")
    private ResponseResult getMenuList(){
        return menuService.getMenuList();
    }
}
