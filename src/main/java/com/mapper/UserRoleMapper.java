package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.domain.UserRole;

/**
* @author 23340
* @description 针对表【blog_user_role】的数据库操作Mapper
* @createDate 2022-08-17 08:24:01
* @Entity com.domain.UserRole
*/
public interface UserRoleMapper extends BaseMapper<UserRole> {

    String findRole(Long id);
}




