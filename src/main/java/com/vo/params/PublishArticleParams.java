package com.vo.params;

import com.vo.TagVo;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * <h4>blog_admin</h4>
 * <p>发布文章参数</p>
 *
 * @author : zlz
 * @date : 2022-08-31 09:53
 **/
@Data
public class PublishArticleParams {
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
     * 文章权重
     */
    private Integer weight;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 文章摘要
     */
    private String summary;
    /**
     * 文章标签id
     */
    private Long tagId;
}
