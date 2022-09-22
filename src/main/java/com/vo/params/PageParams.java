package com.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <h4>blog_admin</h4>
 * <p>分页</p>
 *
 * @author : zlz
 * @date : 2022-08-17 07:49
 **/
@Data
@ApiModel(description = "实体类", value = "分页参数")
public class PageParams {
    @ApiModelProperty(name = "page",value = "起始页",required = true,example = "1")
    private  int page=1;
    @ApiModelProperty(name = "pageSize",value = "每页数量",required = true,example = "10")
    private  int pageSize=20;
    @ApiModelProperty(name = "query",value = "模糊查询")
    private String query;
}
