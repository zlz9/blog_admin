package com.vo.params;

import lombok.Data;

/**
 * <h4>blog_admin</h4>
 * <p>评论参数</p>
 *
 * @author : zlz
 * @date : 2022-08-25 10:55
 **/
@Data
public class CommentParams {
    private Long articleId;
    private String content;
    private Long parent;
    private Long toUserId;
}
