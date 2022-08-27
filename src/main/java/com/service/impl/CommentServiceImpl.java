package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.domain.Comment;
import com.domain.LoginUser;
import com.domain.User;
import com.mapper.CommentMapper;
import com.service.CommentService;
import com.service.UserService;
import com.utils.ResponseResult;
import com.vo.CommentVo;
import com.vo.UserVo;
import com.vo.params.CommentParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 23340
 * @description 针对表【blog_comment】的数据库操作Service实现
 * @createDate 2022-08-24 17:09:08
 */
@Slf4j
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserService userService;

    @Override
    public ResponseResult getCommentByArticleId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId,id);
        queryWrapper.eq(Comment::getLevel,1);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        List<CommentVo> commentVoList = copyList(comments);
        return new ResponseResult<>(200,commentVoList);
    }

    @Override
    public ResponseResult comment(CommentParams commentParams) {
        /**
         * 1.获取当前登录信息
         */
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = loginUser.getUser();
        Comment comment = new Comment();
        comment.setArticleId(commentParams.getArticleId());
        comment.setAuthorId(user.getId());
        comment.setCreateTime(System.currentTimeMillis());
        comment.setComment(commentParams.getContent());
        Long parent = commentParams.getParent();
        if (parent == null || parent==0) {
            comment.setLevel(1);
        }else {
            comment.setLevel(2);
        }
        comment.setParentId(parent == null ? 0 : parent);
        Long toUserId = commentParams.getToUserId();
        comment.setToUid(toUserId == null ? 0 : toUserId );
        this.commentMapper.insert(comment);
        return new ResponseResult<>(200,"评论成功  ");
    }

    private List<CommentVo> copyList(List<Comment> comments) {
        List<CommentVo> commentVoList = new ArrayList<>();
        for (Comment comment : comments) {
            commentVoList.add(copy(comment));
        }
        return commentVoList;
    }

    private CommentVo copy(Comment comment) {
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment, commentVo);
//        作者信息
        Long authorId = comment.getAuthorId();
        UserVo userVo = this.userService.findUserVoById(authorId);
        commentVo.setUserVo(userVo);
//        子评论
        Integer level = comment.getLevel();
        if (1 == level) {
            Long id = comment.getId();
            List<CommentVo> commentVoList = findCommentsByParentId(id);
            commentVo.setChildren(commentVoList);
        }
//        to user 给谁评论
        if (level > 1) {
            Long toUid = comment.getToUid();
            this.userService.findUserVoById(toUid);
        }
        return commentVo;
    }

    private List<CommentVo> findCommentsByParentId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getParentId, id);
        queryWrapper.eq(Comment::getLevel, 2);
        return copyList(commentMapper.selectList(queryWrapper));
    }
}

