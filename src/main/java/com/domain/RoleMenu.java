package com.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName blog_role_menu
 */
@TableName(value ="blog_role_menu")
@Data
public class RoleMenu implements Serializable {
    /**
     * 角色id
     */
    //    防止精度丢失
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;

    /**
     * 菜单id
     */
    private Long menuId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}