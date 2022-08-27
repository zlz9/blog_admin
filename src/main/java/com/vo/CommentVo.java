package com.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <h4>blog_admin</h4>
 * <p>文章评论模块</p>
 *
 * @author : zlz
 * @date : 2022-08-24 16:34
 **/
@Data
public class CommentVo {
    /**
     * 用户信息
     */
    private UserVo userVo;
    /**
     * 评论
     */
    private String comment;
    /**
     * 评论文章id
     */
    private Long articleId;
    /**
     * 父级评论
     */
    private Long parentId;
    /**
     * 回复给谁
     */
    private Long toUid;

    /**
     * 评论层级
     */
    private Integer level;
    /**
     * 创建时间
     */
    private Long createTime;
    private List<CommentVo> children = new ArrayList<>();
}
