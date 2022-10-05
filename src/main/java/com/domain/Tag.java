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
 * @TableName blog_tag
 */
@TableName(value ="blog_tag")
@Data
public class Tag implements Serializable {
    /**
     * 标签id
     */
    @TableId(type = IdType.AUTO)
    //    防止精度丢失
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tagId;

    /**
     * 标签名
     */
    private String tagName;
    /**
     * 标签封面
     */
    private String tagCover;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}