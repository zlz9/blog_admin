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
 * @TableName blog_work_img
 */
@TableName(value ="blog_work_img")
@Data
public class WorkImg implements Serializable {
    /**
     * 图片id
     */
    @TableId(type = IdType.AUTO)
//    防止精度丢失
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 图片资源
     */
    private String url;

    /**
     * 作品id
     */
    private Long workId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}