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
 * @TableName blog_work
 */
@TableName(value ="blog_work")
@Data
public class Work implements Serializable {
    /**
     * id
     */
    //    防止精度丢失
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 作品名
     */
    private String name;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 作品描述
     */
    private String description;

    /**
     * 作者id
     */
    private Long authorId;

    /**
     * 1正常，0异常
     */
    private Boolean status;

    /**
     * github(链接)
     */
    private String linkGithub;

    /**
     * gitee(链接)
     */
    private String linkGitee;
    /**
     * 线上预览
     */
    private String preview;
    /**
     * 产品定位
     */
    private String position;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}