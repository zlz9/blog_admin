package com.controller;

import com.service.CommentService;
import com.utils.ResponseResult;
import com.vo.params.CommentPageParams;
import com.vo.params.CommentParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <h4>blog_admin</h4>
 * <p>文章回复模块</p>
 *
 * @author : zlz
 * @date : 2022-08-24 17:17
 **/
@Api(tags = "评论模块")
@RestController
@RequestMapping("api")
public class CommentController {
    @Autowired
    private CommentService commentService;

     @ApiOperation(value = "文章的评论",notes = "分页查询文章的所有评论")
     @ApiImplicitParam(name = "id",value = "要查询的文章id")
    /**
     * 文章评论
     * @param id
     * @return
     */
    @GetMapping("/article/comment")
    public ResponseResult getCommentByArticleId(CommentPageParams commentPageParams){
        return commentService.getCommentByArticleId(commentPageParams);
    }
   @ApiOperation(value ="发布评论",notes = "发布文章的所有评论")
    /**
     * 发布评论
     * @param commentParams
     * @return
     */
    @PostMapping("article/create/comment")
    public ResponseResult comment(@RequestBody CommentParams commentParams){
        return commentService.comment(commentParams);
    }
}
