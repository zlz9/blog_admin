package com.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * <h4>blog_admin</h4>
 * <p>文章参数</p>
 *
 * @author : zlz
 * @date : 2022-08-16 08:52
 **/
@Data
public class ArticleVo {
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章标签
     */
    private List<TagVo> tags;

    /**
     * 文章html
     */
    private String bodyHtml;

    /**
     * 文章md
     */
    private String bodyMd;

    /**
     * 文章阅读数
     */
    private Integer viewCount;

    /**
     * 权重
     */
    private Integer weight;
    private Date createTime;
    private String author;
    /**
     * 文章标题
     */
//   TODO 暂时不做评论系统
}