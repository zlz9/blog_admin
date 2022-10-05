package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.domain.LoginUser;
import com.domain.User;
import com.domain.UserRole;
import com.lang.Const;
import com.mapper.UserMapper;
import com.mapper.UserRoleMapper;
import com.service.UserService;
import com.utils.RedisCache;
import com.utils.ResponseResult;
import com.utils.UserNameUtils;
import com.vo.UserVo;
import com.vo.params.RegisterParams;
import com.vo.params.UserParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
* @author 23340
* @description 针对表【blog_user】的数据库操作Service实现
* @createDate 2022-08-13 11:02:25
*/
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private UserRoleMapper userRoleMapper;

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

    /**
     * 用户注册模块
     * 1.比对验证码
     * 2.获取redis中的验证码
     * 3.验证码比对成功，注册
     * 4.注册用户并且绑定角色
     * @param registerParams
     * @return
     */
    @Override
    public ResponseResult register(RegisterParams registerParams) {
        User user = new User();
        if (registerParams.getCode()==null) {
            return new ResponseResult<>(444,"请输入验证码");
        }
        String ParamsCode = registerParams.getCode();
        String code = redisCache.getCacheObject("code");
        if (!ParamsCode.equals(code)) {
         return new ResponseResult<>(443,"验证码错误！请重新获取验证码");
        }else if (StringUtils.isBlank(code)) {
            return new ResponseResult<>(446,"验证码过期");
        }
//        比对qq邮箱查看是否已经注册
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail, registerParams.getEmail());
        User hasRegister = userMapper.selectOne(wrapper);
        if (hasRegister != null) {
            return new ResponseResult<>(445,"该用户已经注册");
        }
//      默认头像
        user.setAvator(Const.REGISTER_AVATAR);
//        默认密码
        String encodePassword = passwordEncoder.encode(registerParams.getPassword());
//        设置用户名
        user.setUserName(registerParams.getUserName());
        user.setPassword(encodePassword);
        user.setEmail(registerParams.getEmail());
        user.setNickName(UserNameUtils.getRandomJianHan(4));
//        绑定角色
        userMapper.insert(user);
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(Const.REGISTER_ID);
        userRoleMapper.insert(userRole);
//        注册成功后删除redis中的code
        redisCache.deleteObject("code");
        return new ResponseResult(200, "注册成功");
    }

    /**
     * 更新用户信息模块
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
        user.setAvator(userParams.getAvator());
        user.setUserName(userParams.getUserName());
        user.setNickName(userParams.getNickName());
        user.setCreateTime(System.currentTimeMillis());
        user.setPhoneNumber(userParams.getPhone());
        user.setEmail(userParams.getEmail());
        user.setMotto(userParams.getMotto());
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
        userParams.setMotto(user.getMotto());
        userParams.setBirthday(user.getBirthday());
//        userParams.setId(user.getId());
        Boolean sex = user.getSex();
        if (!sex) {
            userParams.setSex("男");
        }
        else {
            userParams.setSex("女");
        }
        userParams.setNickName(user.getNickName());
        userParams.setPhone(user.getPhoneNumber());
        if (user == null) {
            return new ResponseResult<>(400, "用户不存在");
        }
        return new ResponseResult<>(200, userParams);
    }
}




