package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.domain.UserRole;
import com.service.UserRoleService;
import com.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 23340
* @description 针对表【blog_user_role】的数据库操作Service实现
* @createDate 2022-08-17 08:24:01
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




