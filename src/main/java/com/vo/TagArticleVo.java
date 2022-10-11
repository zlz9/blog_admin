package com.vo;

import lombok.Data;

/**
 * <h4>blog_admin</h4>
 * <p>标签对应的文章数</p>
 *
 * @author : zlz
 * @date : 2022-10-10 10:23
 **/
@Data
public class TagArticleVo {
    private String tagName;
    private Integer articleNum;
}
