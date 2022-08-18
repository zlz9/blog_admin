package com.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName blog_tag
 */
@TableName(value ="blog_tag")
@Data
public class Tag implements Serializable {
    /**
     * 标签id
     */
    @TableId(type = IdType.AUTO)
    private Long tagId;

    /**
     * 标签名
     */
    private String tagName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}