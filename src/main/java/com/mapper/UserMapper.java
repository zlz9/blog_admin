package com.mapper;

import com.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 23340
* @description 针对表【blog_user】的数据库操作Mapper
* @createDate 2022-08-13 11:02:25
* @Entity com.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




