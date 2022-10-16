package com.vo;

import lombok.Data;

/**
 * <h4>blog_admin</h4>
 * <p>面试vo模块</p>
 *
 * @author : zlz
 * @date : 2022-09-29 13:04
 **/
@Data
public class InterViewVo {

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
}
