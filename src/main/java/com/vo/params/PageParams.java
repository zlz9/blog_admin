package com.vo.params;

import lombok.Data;

/**
 * <h4>blog_admin</h4>
 * <p>分页</p>
 *
 * @author : zlz
 * @date : 2022-08-17 07:49
 **/
@Data
public class PageParams {
    private  int page=1;
    private  int pageSize=10;
}
