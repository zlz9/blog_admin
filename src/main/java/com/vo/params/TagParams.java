package com.vo.params;

import lombok.Data;

/**
 * <h4>blog_admin</h4>
 * <p>标签</p>
 *
 * @author : zlz
 * @date : 2022-10-06 00:39
 **/
@Data
public class TagParams {
    /**
     * 标签名
     */
    private String tagName;
    /**
     * 标签封面
     */
    private String tagCover;
}
