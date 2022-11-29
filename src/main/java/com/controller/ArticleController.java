package com.controller;

import com.annocation.Limit;
import com.service.ArticleService;
import com.utils.ResponseResult;
import com.vo.ArticleInfoVo;
import com.vo.params.LikeParams;
import com.vo.params.PageParams;
import com.vo.params.PublishArticleParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

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
    ArticleService articleService;
    /**
     * 分页查询所有文章
     * 添加请求访问限制
     */
    @ApiOperation(value = "获取文章",notes = "文章分页，必填")
    @GetMapping("article")
    @Limit(key = "limit3", permitsPerSecond = 1, timeout = 500, timeunit = TimeUnit.MILLISECONDS,msg = "系统繁忙，请稍后再试！")
    public ResponseResult getArticle(PageParams pageParams){
        return articleService.getArticle(pageParams);
    }
    /**
     *根据id删除文章
     * @param id
     * @return
     */
    @ApiOperation(value = "删除文章",notes = "删除文章，传入文章id")
    @ApiImplicitParam(name = "id" ,value = "文章id",required = true)
    @PostMapping("delelet/article/id={id}")
    public ResponseResult delArticleById(@PathVariable Integer id){
        return articleService.delArticleById(id);
    }
    @ApiOperation(value = "查询当前用户的文章",notes = "根据当前用户查询文章，请求头带token")
    /**
     * 根据当前用户id分页查询文章
     */
    @GetMapping("article/author")
    public ResponseResult getArticleByAuthorId(PageParams pageParams){
        return articleService.getArticleByAuthorId(pageParams);
    }
    @ApiOperation(value = "发布文章")
    /**
     * 发布文章
     */
    @PostMapping("/publish")
    public ResponseResult publishArticle(@RequestBody PublishArticleParams publishArticleParams){
        return articleService.publishArticle(publishArticleParams);
    }

    /**
     * 文章详情
     * @param id
     * @return
     */
    @ApiOperation(value = "文章详情",notes = "传入文章id")
    @ApiImplicitParam(name = "id",value = "文章id",required = true)
    @GetMapping ("/article/{id}")
    public ResponseResult articleInfo(@PathVariable Long id){
        return articleService.articleInfo(id);
    }

    /**
     * 文章推荐
     */
    @ApiOperation(value = "文章推送",notes = "随机推荐文章")
    @GetMapping("/article/recommend")
    public ResponseResult recommendArticle(){
        return articleService.recommendArticle();
    }
    /**
     * 修改文章
     */
    @ApiOperation(value = "更改文章",notes = "更新文章内容，包括标签,标题")
    @PostMapping("/article/update")
    public ResponseResult updateArticle(@RequestBody ArticleInfoVo articleInfoVo){
        return articleService.updateArticle(articleInfoVo);
    }
    /**
     * 文章点赞
     */
    @PostMapping("article/like")
    public ResponseResult articleLike(@RequestBody LikeParams likeParams){
        return articleService.Liked(likeParams);
    }
    /**
     * 查询文章标题
     */
    @GetMapping("article/search/{title}")
    public ResponseResult articleSearch(@PathVariable String title){
        return articleService.searchArticle(title);
    }
}