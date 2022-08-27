package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.domain.Role;
import com.service.RoleService;
import com.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 23340
* @description 针对表【blog_role】的数据库操作Service实现
* @createDate 2022-08-17 08:24:01
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




