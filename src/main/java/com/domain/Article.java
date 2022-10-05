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
 * @TableName blog_article
 */
@TableName(value ="blog_article")
@Data
public class Article implements Serializable {
    /**
     * 文章id
     */
    @TableId(type = IdType.AUTO)
    //    防止精度丢失
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 文章评论
     */
    private Long commentId;

    /**
     * 文章作者
     */
    private Long authorId;

    /**
     * 发布时间
     */
    private Long createTime;

    /**
     * 是否删除(0，正常；1删除)
     */
    private Integer delFlag;

    /**
     * 文章html
     */
    private String bodyHtml;

    /**
     * 文章md
     */
    private String bodyMd;

    /**
     * 文章阅读数
     */
    private Integer viewCount;

    /**
     * 权重
     */
    private Integer weight;
    /**
     * 文章标题
     */
  private String title;
    /**
     * 点赞数量
     */
  private Integer likeCount;
  private String summary;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}