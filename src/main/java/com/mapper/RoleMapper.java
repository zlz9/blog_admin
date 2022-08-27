package com.mapper;

import com.domain.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 23340
* @description 针对表【blog_role】的数据库操作Mapper
* @createDate 2022-08-17 08:24:01
* @Entity com.domain.Role
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}




