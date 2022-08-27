package com.controller;

import com.service.CommentService;
import com.utils.ResponseResult;
import com.vo.params.CommentParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <h4>blog_admin</h4>
 * <p>文章回复模块</p>
 *
 * @author : zlz
 * @date : 2022-08-24 17:17
 **/
@RestController
@RequestMapping("api")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/article/comment/{id}")
    public ResponseResult getCommentByArticleId(@PathVariable Long id){
        return commentService.getCommentByArticleId(id);
    }
    @PostMapping("article/create/comment")
    public ResponseResult comment(@RequestBody CommentParams commentParams){
        return commentService.comment(commentParams);
    }
}
