package com.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.domain.LoginUser;
import com.domain.User;
import com.mapper.UserMapper;
import com.service.UserService;
import com.utils.ResponseResult;
import com.vo.UserVo;
import com.vo.params.RegisterParams;
import com.vo.params.UserParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
* @author 23340
* @description 针对表【blog_user】的数据库操作Service实现
* @createDate 2022-08-13 11:02:25
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserVo findUserVoById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            user = new User();
            user.setNickName("快乐的小猪仔");
//            TODO 填写默认头像
            user.setAvator("这里填写默认值");
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }

    @Override
    public User findAuthorById(Long authorId) {
        User user = userMapper.selectById(authorId);
        if (user == null) {
            user = new User();
            user.setNickName("快乐的小猪仔");
        }
        return user;
    }

    @Override
    public ResponseResult register(RegisterParams registerParams) {
        User user = new User();
        user.setUserName(registerParams.getUserName());
        String password = registerParams.getPassword();
        String encodePassword = passwordEncoder.encode(password);
        user.setPassword(encodePassword);
        userMapper.insert(user);
        return new ResponseResult(200, "注册成功");
    }

    /**
     * 填写用户信息模块
     * @param userParams
     * @return
     */
    @Override
    public ResponseResult FillUserInfo(UserParams userParams) {

        /**
         * 判断参数是否为空
         */
        if (ObjectUtils.isNull(userParams)) {
            return new ResponseResult(500, "参数错误");
        }
        //        获取SecurityContextHolder的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        User user = userMapper.selectById(userid);
        user.setAge(userParams.getAge());
        user.setAvator(userParams.getAvator());
        user.setNickName(userParams.getNickName());
        user.setCreateTime(System.currentTimeMillis());
        user.setPhoneNumber(userParams.getPhoneNumber());
        user.setEmail(userParams.getEmail());
        String sex = userParams.getSex();
        if (sex=="女") {
            user.setSex(true);
        }else {
            user.setSex(false);
        }

        if (userid == null) {
            throw new RuntimeException("用户更新失败");
        }
        userMapper.updateById(user);
        return new ResponseResult<>(200, "更新成功");
    }

    /**
     * 用户详情模块
     * @param
     * @return
     */
    @Override
    public ResponseResult getUserInfo() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser  loginUser = (LoginUser) authentication.getPrincipal();
//        当前用户信息
        Long id = loginUser.getUser().getId();
        User user = userMapper.selectById(id);
        UserParams userParams = new UserParams();
        userParams.setAge(user.getAge());
        userParams.setAvator(user.getAvator());
        userParams.setEmail(user.getEmail());
//        userParams.setId(user.getId());
        Boolean sex = user.getSex();
        if (!sex) {
            userParams.setSex("男");
        }
        else {
            userParams.setSex("女");
        }

        userParams.setNickName(user.getNickName());
        userParams.setPhoneNumber(user.getPhoneNumber());
        if (user == null) {
            return new ResponseResult<>(400, "用户不存在");
        }
        return new ResponseResult<>(200, userParams);
    }
}




