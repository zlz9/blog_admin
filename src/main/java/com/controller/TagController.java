package com.controller;

import com.service.TagService;
import com.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <h4>blog_admin</h4>
 * <p>标签管理模块</p>
 * @author : zlz
 * @date : 2022-08-18 16:44
 **/
@Api(tags = "文章标签模块")
@RestController
@RequestMapping("api")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 添加文章标签模块
     * @param tagName
     * @return
     */
    @ApiOperation(value = "添加文章标签",notes = "添加文章标签，路径参数{tagName}必填")
    @ApiImplicitParam(name = "{tagName}",value = "要添加的文章标签")
    @PostMapping("article/tag/add/{tagName}")
    private ResponseResult addTag(@PathVariable String tagName){
        return tagService.addTag(tagName);
    }

    /**
     * 获取文章标签列表
     * @return
     */
    @ApiOperation(value = "获取文章标签列表",notes = "获取所有标签")
    @GetMapping("tagList")
    public ResponseResult getTagList(){
        return tagService.getTagList();
    }

}
