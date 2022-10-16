package com.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName blog_interview
 */
@TableName(value ="blog_interview")
@Data
public class Interview implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 面试名称
     */
    private String name;

    /**
     * 介绍
     */
    private String summary;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 封面
     */
    private String cover;

    /**
     * 作者id
     */
    private Long authorId;

    /**
     * 链接
     */
    private String link;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}