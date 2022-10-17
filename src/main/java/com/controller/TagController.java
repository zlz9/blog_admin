package com.controller;

import com.service.TagService;
import com.utils.ResponseResult;
import com.vo.params.TagPageParams;
import com.vo.params.TagParams;
import io.swagger.annotations.Api;
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
    @ApiOperation(value = "添加文章标签",notes = "添加文章标签")
    @PostMapping("article/tag/add")
    private ResponseResult addTag(@RequestBody TagParams tagParams){
        return tagService.addTag(tagParams);
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

    /**
     * params 文章id
     * @return
     */
    @ApiOperation(value = "根据标签获取文章",notes = "根据所选标签获取文章")
    @GetMapping("tag/articles")
    public ResponseResult getArticleByTag( TagPageParams tagPageParams){return tagService.getArticleByTag(tagPageParams);}
    /**
     * 删除标签
     */
    @ApiOperation(value = "删除文章标签")
    @GetMapping("/delete/tag/{id}")
    public ResponseResult delTag(@PathVariable Long id){
        return tagService.delTag(id);
    }

}
