package com.vo.params;

import lombok.Data;

/**
 * <h4>blog_admin</h4>
 * <p>tool参数</p>
 *
 * @author : zlz
 * @date : 2022-09-29 19:46
 **/
@Data
public class ToolParams {
    /**
     * 名称
     */
    private String name;

    /**
     * 链接
     */
    private String link;

    /**
     * 作者id
     */
    private Long authorId;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 描述
     */
    private String summary;

    /**
     * 封面
     */
    private String cover;
}
