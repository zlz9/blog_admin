package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.domain.Tool;

/**
* @author 23340
* @description 针对表【blog_tool】的数据库操作Mapper
* @createDate 2022-09-29 17:08:23
* @Entity com.domain.Tool
*/
public interface ToolMapper extends BaseMapper<Tool> {

    Integer findToolCountById(Long id);
}




