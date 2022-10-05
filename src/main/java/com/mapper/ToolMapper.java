package com.mapper;

import com.domain.Tool;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 23340
* @description 针对表【blog_tool】的数据库操作Mapper
* @createDate 2022-09-29 17:08:23
* @Entity com.domain.Tool
*/
@Mapper
public interface ToolMapper extends BaseMapper<Tool> {

}




