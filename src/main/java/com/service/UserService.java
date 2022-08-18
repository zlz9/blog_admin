package com.service;

import com.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.utils.ResponseResult;
import com.vo.params.RegisterParams;
import com.vo.params.UserParams;

/**
* @author 23340
* @description 针对表【blog_user】的数据库操作Service
* @createDate 2022-08-13 11:02:25
*/
public interface UserService extends IService<User> {

    User findAuthorById(Long authorId);

    ResponseResult register(RegisterParams registerParams);

    ResponseResult FillUserInfo(UserParams userParams);

    ResponseResult getUserInfo();
}
