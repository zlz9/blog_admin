package com.mapper;

import com.domain.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 23340
* @description 针对表【blog_comment】的数据库操作Mapper
* @createDate 2022-08-24 17:09:08
* @Entity com.domain.Comment
*/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}




