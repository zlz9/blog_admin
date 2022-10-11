package com.controller;

import com.service.UserService;
import com.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h4>blog_admin</h4>
 * <p>系统管理模块</p>
 *
 * @author : zlz
 * @date : 2022-10-07 15:28
 **/
@Api(tags = "系统管理模块")
@RestController
@RequestMapping("api")
public class SystemController {
    @Autowired
    private UserService userService;
    @ApiOperation(value = "统计网站文章分类")
    @GetMapping("/system/user/skills")
    private ResponseResult UserSkills(){
        return userService.UserSkills();
    }
}
