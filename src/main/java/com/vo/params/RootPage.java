package com.vo.params;

import lombok.Data;

/**
 * <h4>blog_admin</h4>
 * <p>所有分页默认参数</p>
 *
 * @author : zlz
 * @date : 2022-10-05 17:20
 **/
@Data
public class RootPage {
    private Long id;
    private  int page=1;
    private  int pageSize=100;
}
