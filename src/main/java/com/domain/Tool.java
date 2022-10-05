package com.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName blog_tool
 */
@TableName(value ="blog_tool")
@Data
public class Tool implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 名称
     */
    private String name;

    /**
     * 链接
     */
    private String link;

    /**
     * 作者id
     */
    private Long authorId;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 描述
     */
    private String summary;

    /**
     * 封面
     */
    private String cover;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}