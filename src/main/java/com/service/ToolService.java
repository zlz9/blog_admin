package com.service;

import com.domain.Tool;
import com.baomidou.mybatisplus.extension.service.IService;
import com.utils.ResponseResult;
import com.vo.params.ToolParams;

/**
* @author 23340
* @description 针对表【blog_tool】的数据库操作Service
* @createDate 2022-09-29 17:08:23
*/
public interface ToolService extends IService<Tool> {

    ResponseResult getTool();

    ResponseResult uploadTool(ToolParams toolParams);

    ResponseResult deleteToolById(Long id);
}
