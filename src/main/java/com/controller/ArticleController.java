package com.controller;

import com.service.ArticleService;
import com.utils.ResponseResult;
import com.vo.params.PageParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <h4>blog_admin</h4>
 * <p>文章模块</p>
 *
 * @author : zlz
 * @date : 2022-08-16 08:43
 **/
@Api(tags = "文章模块")
@RestController
@RequestMapping("api")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    /**
     * 根据当前用户id分页查询文章
     */
    @ApiOperation(value = "获取文章",notes = "文章分页，必填")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "文章分页", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true)
    })

    @GetMapping("article")
    public ResponseResult getArticle(@RequestBody PageParams pageParams){
        return articleService.getArticle(pageParams);
    }

    @ApiOperation(value = "删除文章",notes = "删除文章，传入文章id")
    @ApiImplicitParam(name = "id" ,value = "文章id",required = true)
    @PostMapping("delelet/article/id={id}")
    public ResponseResult delArticleById(@PathVariable Integer id){
        return articleService.delArticleById(id);
    }
}
