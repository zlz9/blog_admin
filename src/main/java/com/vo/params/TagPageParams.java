package com.vo.params;

import lombok.Data;

/**
 * <h4>blog_admin</h4>
 * <p>根据标签查找文章</p>
 *
 * @author : zlz
 * @date : 2022-09-08 14:21
 **/
@Data
public class TagPageParams {
    private Long id;
    private int page=1;
    private int pageSize = 10;
}
