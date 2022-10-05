package com.mapper;

import com.domain.Interview;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 23340
* @description 针对表【blog_interview】的数据库操作Mapper
* @createDate 2022-09-29 11:15:33
* @Entity com.domain.Interview
*/
@Mapper
public interface InterviewMapper extends BaseMapper<Interview> {

}




