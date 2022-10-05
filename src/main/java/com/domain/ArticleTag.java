package com.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName blog_article_tag
 */
@TableName(value ="blog_article_tag")
@Data
public class ArticleTag implements Serializable {
    /**
     * 
     */
    @TableId
    //    防止精度丢失
    @JsonSerialize(using = ToStringSerializer.class)
    private Long articleId;

    /**
     * 
     */
    private Long tagId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}