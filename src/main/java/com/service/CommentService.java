package com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.domain.Comment;
import com.utils.ResponseResult;
import com.vo.params.CommentPageParams;
import com.vo.params.CommentParams;

/**
* @author 23340
* @description 针对表【blog_comment】的数据库操作Service
* @createDate 2022-08-24 17:09:08
*/
public interface CommentService extends IService<Comment> {

    ResponseResult getCommentByArticleId(CommentPageParams commentPageParams);

    ResponseResult comment(CommentParams commentParams);
}
