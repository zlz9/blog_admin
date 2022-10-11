package com.vo.params;

import lombok.Data;

/**
 * <h4>blog_admin</h4>
 * <p>文章点赞</p>
 *
 * @author : zlz
 * @date : 2022-10-05 12:14
 **/
@Data
public class LikeParams {
    private Long articleId;
    private Long userId;
    private boolean status;
    private Long createTime;
}
