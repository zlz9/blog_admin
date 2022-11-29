package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.domain.Work;

/**
* @author 23340
* @description 针对表【blog_work】的数据库操作Mapper
* @createDate 2022-09-22 16:34:10
* @Entity com.domain.Work
*/
public interface WorkMapper extends BaseMapper<Work> {

    Integer findWorkCountById(Long id);
}




