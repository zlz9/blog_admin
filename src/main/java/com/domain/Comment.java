package com.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName blog_comment
 */
@TableName(value ="blog_comment")
@Data
public class Comment implements Serializable {
    /**
     * 评论id
     */
    @TableId(type = IdType.AUTO)
//    把Long转化成字符串
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 用户id
     */
    private Long authorId;

    /**
     * 评论信息
     */
    private String comment;

    /**
     * 上级评论id
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}