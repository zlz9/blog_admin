package com.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName blog_user_role
 */
@TableName(value ="blog_user_role")
@Data
public class UserRole implements Serializable {
    /**
     * 用户id
     */
    //    防止精度丢
    @TableId
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}