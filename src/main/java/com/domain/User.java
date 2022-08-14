package com.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName blog_user
 */
@TableName(value ="blog_user")
@Data
public class User implements Serializable {
    /**
     * 用户 id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别（0男，1女）
     */
    private Boolean sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 注册时间
     */
    private Date createTime;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 头像
     */
    private String avator;

    /**
     * 状态（0正常，1不正常）
     */
    private Boolean status;

    /**
     * 手机号
     */
    private Integer phoneNumber;

    /**
     * 删除（0正常，1删除）
     */
    private Boolean delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}