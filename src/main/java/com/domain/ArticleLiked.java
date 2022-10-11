package com.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName blog_article_liked
 */
@TableName(value ="blog_article_liked")
@Data
public class ArticleLiked implements Serializable {
    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 点赞者的id
     */
    private Long userId;

    /**
     * 点赞的状态，1标识已赞，0标识取消赞
     */
    private Boolean likeStatus;
    /**
     * 创建时间
     */
    private Long createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}