package com.vo.params;

import lombok.Data;

/**
 * <h4>blog_admin</h4>
 * <p>评论分页</p>
 *
 * @author : zlz
 * @date : 2022-10-05 17:05
 **/
@Data
public class CommentPageParams {
    private Long id;
    private  int page=1;
    private  int pageSize=15;
}
