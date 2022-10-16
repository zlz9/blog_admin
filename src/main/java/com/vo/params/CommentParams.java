package com.vo.params;

import lombok.Data;

/**
 * <h4>blog_admin</h4>
 * <p>评论参数</p>
 *
 * @author : zlz
 * @date : 2022-08-25 10:55
 **/
@Data
public class CommentParams {
    /**
     * 文章id
     */
    private Long articleId;
    /**
     * 评内容
     */
    private String content;
    /**
     * 父id
     */
    private Long parentId;
    /**
     * 回复谁的评论
     */
    private Long toUserId;
    /**
     * 层级
     */
    private Integer level;
}
