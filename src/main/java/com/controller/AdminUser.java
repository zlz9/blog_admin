package com.controller;

import com.service.ArticleService;
import com.service.UserService;
import com.service.WorkService;
import com.utils.ResponseResult;
import com.vo.params.PwdParams;
import com.vo.params.RoleParams;
import com.vo.params.RootPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <h4>blog_admin</h4>
 * <p>用户管理模块</p>
 *
 * @author : zlz
 * @date : 2022-10-06 10:37
 **/
@Api(tags = "用户管理模块")
@RestController
@RequestMapping("api")
public class AdminUser {
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private WorkService workService;
    @ApiOperation(value = "分页查询所有用户信息")
    @GetMapping("/user/all")
    private ResponseResult findAllUser(RootPage rootPage){
        return userService.findAllUser(rootPage);
    }
    @GetMapping("/user/{id}")
    @ApiOperation(value = "根据id查询用户详细信息")
    private ResponseResult findUserById(@PathVariable Long id){
        return userService.findUserDetailsById(id);
    }
    @ApiOperation(value = "设置用户信息")
    @PostMapping("/user/role")
    private ResponseResult setRole(@RequestBody RoleParams roleParams){
        return userService.setRole(roleParams);
    }
    @ApiOperation(value = "强制用户下线并且锁定")
    @PostMapping("/user/lock/{id}")
    private ResponseResult lockAccount(@PathVariable Long id){
        return userService.lockAccount(id);
    }
    @ApiOperation(value = "解锁用户")
    @PostMapping("/user/unlock/{id}")
    private ResponseResult unlockAccount(@PathVariable Long id){
        return userService.unlockAccount(id);
    }
    @ApiOperation(value = "查询锁定用户")
    @GetMapping("/user/locked")
    private ResponseResult findLockUser(){
        return userService.findLockUser();
    }
    @ApiOperation(value ="查询当前用户30天的文章数")
    @GetMapping("/user/month/article")
    private ResponseResult findSevenArticle(){
        return articleService.getArticleMonth();
    }
    @ApiOperation(value = "搜索用户昵称")
    @GetMapping("/user/search/{nickName}")
    private ResponseResult findUserByNickName(@PathVariable String nickName){
        return userService.findUserByNickName(nickName);
    }
    @ApiOperation("统计用户文章分布")
    @GetMapping ("/user/skill")
    private ResponseResult getUserSkills(){
        return  userService.getUserSkills();
    }
    @ApiOperation("统计用户文章的阅读数")
    @GetMapping("/user/article/viewCount")
    private ResponseResult viewcount(){
        return articleService.getArticleView();
    }
    @ApiOperation("统计用户作品数")
    @GetMapping("/user/work/count")
    private ResponseResult workCount(){
        return workService.getWorkCount();
    }
    @ApiOperation("重置用户密码")
    @GetMapping("/reload/password/{id}")
    private ResponseResult reloadPassword(@PathVariable Long id){
        return userService.reloadPassword(id);
    }
    @ApiOperation("修改用户密码")
    @PostMapping("/user/newpassword")
    private ResponseResult newPassword(@RequestBody PwdParams pwdParams){
        return userService.newPassword(pwdParams);
    }
}