package com.service;

import com.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.utils.ResponseResult;
import com.vo.UserVo;
import com.vo.params.RegisterParams;
import com.vo.params.RoleParams;
import com.vo.params.RootPage;
import com.vo.params.UserParams;

/**
* @author 23340
* @description 针对表【blog_user】的数据库操作Service
* @createDate 2022-08-13 11:02:25
*/
public interface UserService extends IService<User> {
     UserVo findUserVoById(Long id);
    User findAuthorById(Long authorId);

    ResponseResult register(RegisterParams registerParams);

    ResponseResult FillUserInfo(UserParams userParams);

    ResponseResult getUserInfo();

    ResponseResult findAllUser(RootPage rootPage);

    ResponseResult findUserDetailsById(Long id);

    ResponseResult setRole(RoleParams roleParams);

    ResponseResult lockAccount(Long id);

    ResponseResult unlockAccount(Long id);

    ResponseResult findLockUser();

    ResponseResult findUserByNickName(String nickName);

    ResponseResult UserSkills();

    ResponseResult userSkills();
}
