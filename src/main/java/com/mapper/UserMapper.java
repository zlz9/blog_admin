package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 23340
* @description 针对表【blog_user】的数据库操作Mapper
* @createDate 2022-08-13 11:02:25
* @Entity com.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {


    Page<User> findAllUser(Page<User> page);

    Long findUserRoleById(Long id);

    User findRepeatUser(String userName, String nickName);
}




