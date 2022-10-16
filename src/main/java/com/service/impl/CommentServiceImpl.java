package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import com.vo.params.CommentPageParams;
import com.vo.params.CommentParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 23340
 * @description 针对表【blog_comment】的数据库操作Service实现
 * @createDate 2022-08-24 17:09:08
 */
@Slf4j
@Service
@Transactional
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserService userService;

    /**
     * 根据文章id分页获取评论
     * @return
     */
    @Override
    public ResponseResult getCommentByArticleId(CommentPageParams commentPageParams) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        Page<Comment> page = new Page<>(commentPageParams.getPage(), commentPageParams.getPageSize());
        queryWrapper.eq(Comment::getArticleId,commentPageParams.getId());
        queryWrapper.eq(Comment::getLevel,1);
        queryWrapper.orderByDesc(Comment::getCreateDate);
        Page<Comment> commentPage = commentMapper.selectPage(page,queryWrapper);
        List<Comment> records = commentPage.getRecords();
        List<CommentVo> commentVoList = copyList(records);
        return new ResponseResult<>(200,commentVoList);
    }

    /**
     * 评论功能
     * @param commentParams
     * @return
     */
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
        comment.setCreateDate(System.currentTimeMillis());
        comment.setContent(commentParams.getContent());
        Long parent = commentParams.getParentId();
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
        HashMap<String, Object> map = new HashMap<>();
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
        commentVo.setAuthor(userVo);
//        子评论
        Integer level = comment.getLevel();
        if (1 == level) {
            Long id = comment.getId();
            List<CommentVo> commentVoList = findCommentsByParentId(id);
            if (CollectionUtils.isEmpty(commentVoList)) {
                commentVo.setChildrens(null);
            }
            int size = commentVoList.size();
            commentVo.setChildrens(commentVoList);
        }
//        to user 给谁评论
        if (level > 1) {
            Long toUid = comment.getToUid();
            UserVo toUser = this.userService.findUserVoById(toUid);
            commentVo.setToUser(toUser);
        }
        return commentVo;
    }

    private List<CommentVo> findCommentsByParentId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getParentId, id);
        queryWrapper.eq(Comment::getLevel, 2);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        return copyList(commentMapper.selectList(queryWrapper));
    }
}
//package com.service.impl;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.domain.Comment;
//import com.domain.MyTreeNode;
//import com.domain.User;
//import com.mapper.CommentMapper;
//import com.service.CommentService;
//import com.service.UserService;
//import com.utils.ResponseResult;
//import com.utils.TreeUtils;
//import com.vo.CommentVo;
//import com.vo.UserVo;
//import com.vo.params.CommentParams;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author 23340
// * @description 针对表【blog_comment】的数据库操作Service实现
// * @createDate 2022-08-24 17:09:08
// */
//@Slf4j
//@Service
//@Transactional
//public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
//        implements CommentService {
//    @Autowired
//    private CommentMapper commentMapper;
//    @Autowired
//    private UserService userService;
//
//    /**
//     * 获取文章评论
//     * @param id
//     * @return
//     */
//    @Override
//    public ResponseResult getCommentByArticleId(Long id) {
//        //查出文章的所有评论
//        QueryWrapper<Comment> qw=new QueryWrapper<>();
//        qw.eq("article_id",id);
//        List<Comment> comments = commentMapper.selectList(qw);
//        //所有CommentVo对象的列表
//        List<CommentVo> commentVos=new ArrayList<>();
//        //补充评论内容 copy属性转换为vo
//        for (Comment comment : comments) {
//            CommentVo commentVo=new CommentVo();
//            BeanUtils.copyProperties(comment,commentVo);
//            //作者信息
//            UserVo author = userService.findUserVoById(comment.getAuthorId());
//
//            commentVo.setAuthor(author);
//            //toUser信息 level>1 说明有回复对象
//            if(comment.getLevel()>1){
//                UserVo toUserVo = userService.findUserVoById(comment.getToUid());
//                commentVo.setToUser(toUserVo);
//            }
//            commentVos.add(commentVo);
//        }
//        //构造成树
//        TreeUtils treeUtils = new TreeUtils();
//        MyTreeNode myTree = treeUtils.buildTree(commentVos);
//        //根节点的子节点（即level为1的评论 ）
//        List<MyTreeNode> children = myTree.getChildren();
//        List<CommentVo> level1CommentVo=new ArrayList<>();
//        for (MyTreeNode child : children) {
//            level1CommentVo.add(child.getContent());
//        }
//        return new ResponseResult<>(200, level1CommentVo);
//    }
//
//    /**
//     * 写评论
//     * @param commentParams
//     * @return
//     */
//    @Override
//    public ResponseResult comment(CommentParams commentParams) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        /**
//         * 判断是否有id，如果没有就为顶级评论
//         */
//        UserVo userVo = userService.findUserVoById(user.getId());
//        CommentVo commentVo = new CommentVo();
//
//        return null;
//    }
//}
//
