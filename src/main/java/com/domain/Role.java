package com.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName blog_role
 */
@TableName(value ="blog_role")
@Data
public class Role implements Serializable {
    /**
     * 角色id
     */
    @TableId(type = IdType.AUTO)
    //    防止精度丢失
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 状态（0正常，1异常）
     */
    private Boolean status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否删除（0正常，1删除）
     */
    private Boolean delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}