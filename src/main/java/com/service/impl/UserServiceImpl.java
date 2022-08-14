package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.domain.User;
import com.service.UserService;
import com.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 23340
* @description 针对表【blog_user】的数据库操作Service实现
* @createDate 2022-08-13 11:02:25
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




