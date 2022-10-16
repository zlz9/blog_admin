package com.vo.params;

import lombok.Data;

/**
 * <h4>blog_admin</h4>
 * <p>面试模块参数</p>
 *
 * @author : zlz
 * @date : 2022-09-29 12:46
 **/
@Data
public class InterViewParams {
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
     * 链接
     */
    private String link;

}
