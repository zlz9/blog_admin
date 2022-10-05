package com.vo;

import lombok.Data;

import java.util.List;

/**
 * <h4>blog_admin</h4>
 * <p>文章详情vo</p>
 *
 * @author : zlz
 * @date : 2022-09-05 13:26
 **/
@Data
public class ArticleInfoVo {
    private Long id;
    private String title;
    private String summary;
    private UserVo userVo;
    private List<TagVo> tags;
    private String mdBody;
    private String htmlBody;
    private Long createTime;
    private Integer likeCount;
    private Integer view_count;
    private CommentVo commentVo;
}
