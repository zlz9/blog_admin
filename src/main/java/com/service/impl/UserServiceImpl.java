package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.domain.LoginUser;
import com.domain.User;
import com.domain.UserRole;
import com.lang.Const;
import com.mapper.TagMapper;
import com.mapper.UserMapper;
import com.mapper.UserRoleMapper;
import com.service.*;
import com.utils.RedisCache;
import com.utils.ResponseResult;
import com.utils.UserNameUtils;
import com.vo.TagArticleVo;
import com.vo.TagVo;
import com.vo.UserDetailsVo;
import com.vo.UserVo;
import com.vo.params.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ArticleTagService articleTagService;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private ToolService toolService;
    @Autowired
    private WorkService workService;
    @Autowired
    @Lazy
    private ArticleService articleService;

    @Override
    public UserVo findUserVoById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            user = new User();
            user.setNickName("快乐的小猪仔");
//            TODO 填写默认头像
            user.setAvator("http://qiniu.zhoulizheng.cn/43507260-9412-4a12-88f4-eddb2c3a858f.jpg");
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
//        比对验证码
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
//        查询用户名是否重复
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, registerParams.getUserName());
        User repeatName = userMapper.selectOne(queryWrapper);
        if (!ObjectUtils.isNull(repeatName)) {
            return new ResponseResult<>(400,"用户名重复");
        }
//        设置用户名
        user.setUserName(registerParams.getUserName());
        user.setPassword(encodePassword);
        user.setEmail(registerParams.getEmail());
        user.setNickName(UserNameUtils.getRandomJianHan(4));
//        绑定角色
        userMapper.insert(user);
        UserRole userRole = new UserRole();
        Long id = user.getId();
        userRole.setUserId(id);
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
//        判断用户名或者昵称是否重复
     User repeatUser = userMapper.findRepeatUser(userParams.getUserName(),userParams.getNickName());
        if (!ObjectUtils.isNull(repeatUser)) {
            return new ResponseResult<>(400,"用户名或昵称重复");
        }
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
        userParams.setRole(userRoleMapper.findRole(id));
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

        return new ResponseResult<>(200, userParams);
    }

    /**
     * 查询所有用户信息
     * @return
     */
    @Override
    public ResponseResult findAllUser(RootPage rootPage) {
        Page<User> page = new Page<>(rootPage.getPage(), rootPage.getPageSize());
        //分页查询所有用户信息
       Page<User> userPage= userMapper.findAllUser(page);
        List<User> records = userPage.getRecords();
        return new ResponseResult<>(200,records);
    }

    /**
     * 根据id查找用户详细信息
     * @param id
     * @return
     */
    //TODO 查找用户详细信息
    @Override
    public ResponseResult findUserDetailsById(Long id) {
        UserDetailsVo userDetailsVo = new UserDetailsVo();
        userDetailsVo.setUserInfo(this.findUserVoById(id));
        userDetailsVo.setStatus(userMapper.selectById(id).getStatus());
        userDetailsVo.setArticleCount(articleService.getArticleCountById(id));
        userDetailsVo.setWorkCount(workService.getWorkCountById(id));
        userDetailsVo.setToolCount(toolService.getToolCountById(id));
        userDetailsVo.setTagVos(tagService.getTagsById(id));
        userDetailsVo.setRole(userMapper.findUserRoleById(id));
        User user = userMapper.selectById(id);
        userDetailsVo.setEmail(user.getEmail());
        return new ResponseResult<>(200,userDetailsVo);
    }
    /**
     * 设置用户信息
     * 1.根据id查找角色id
     * 2.查找用户id后判断角色id
     * 3.设置角色为
     * @param roleParams
     * @return
     */
    @Override
    public ResponseResult setRole(RoleParams roleParams) {
        UserRole userRole = new UserRole();
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,roleParams.getId());
        UserRole role = userRoleMapper.selectOne(wrapper);
        if (role.getRoleId()!=1) {
            userRole.setRoleId(roleParams.getRole());
            userRole.setUserId(roleParams.getId());
           userRoleMapper.updateById(userRole);
        }else{
            return new ResponseResult<>(403,"您没有权限");
        }
        return new ResponseResult<>(200,"操作成功");
    }

    /**
     * 用户强制下线,并且锁定用户
     * 1.根据id查询用户
     * 2.将用户锁定用户id
     * 3.查询redis，删除redis中的用户信息
     * @param id
     * @return
     */
    @Override
    public ResponseResult lockAccount(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return new ResponseResult<>(404,"用户不存在");
        }
        redisCache.deleteObject("login:"+user.getId());
        user.setStatus(false);
        userMapper.updateById(user);
        return new ResponseResult<>(200,"操作成功！");
    }

    /**
     * 解锁用户
     * @param id
     * @return
     */
    @Override
    public ResponseResult unlockAccount(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return new ResponseResult<>(404,"用户不存在");
        }
        user.setStatus(true);
        userMapper.updateById(user);
        return new ResponseResult<>(200,"操作成功！");
    }

    /**
     * 查询被锁定的用户
     * @return
     */
    @Override
    public ResponseResult findLockUser() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getStatus,0);
        List<User> users = userMapper.selectList(wrapper);
        List<UserVo> userVoList = new ArrayList<>();
        for (User user : users) {
            userVoList.add(copy(user));
        }
        return new ResponseResult<>(200,userVoList);
    }

    /**
     * 通过用户昵称查询用户
     * @param nickName
     * @return
     */
    @Override
    public ResponseResult findUserByNickName(String nickName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(User::getNickName, nickName);
        List<User> users = userMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(users)) {
            return new ResponseResult(404, "用户不存在");
        }
        return new ResponseResult<>(200,users);
    }
    /**
     * 查询当前文章的标签对应的文章数
     * @return
     */
    @Override
    public ResponseResult UserSkills() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        用户id
        Long id = loginUser.getUser().getId();
//        查询标签数量
        List<TagVo> tagVos = tagMapper.findTagsByUserId(id);
        List<TagArticleVo> tagArticleVos = new ArrayList<>();
        for (TagVo tagVo : tagVos) {
            TagArticleVo tagArticleVo = new TagArticleVo();
            tagArticleVo.setTagName(tagVo.getTagName());
            tagArticleVo.setArticleNum(articleTagService.findArticleNumBytag(tagVo.getTagId()));
            tagArticleVos.add(tagArticleVo);
        }
        return new ResponseResult<>(200,tagArticleVos);
    }

    /**
     * 计算用户标签对应的文章数
     *1。查询作者对应的标签
     * 2.标签for循环查找作者对应的文章数
     * @return
     */
    @Override
    public ResponseResult getUserSkills() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userid = loginUser.getUser().getId();
        List<TagVo> tags = tagMapper.findTagsByUserId(userid);
        List<TagArticleVo> tagArticleVos = new ArrayList<>();
        for (TagVo tag : tags) {
            TagArticleVo tagArticleVo = new TagArticleVo();
//            查找作者对应的文章数
         Integer articleCount = articleService.getArticleCountByUserIdTagId(userid,tag.getTagId());
         tagArticleVo.setArticleNum(articleCount);
         tagArticleVo.setTagName(tag.getTagName());
         tagArticleVos.add(tagArticleVo);
        }
        return new ResponseResult<>(200,tagArticleVos);
    }
    /**TODO 只有超级管理员能访问
     * 重置用户密码
     * 1.根据id查询用户密码
     * 2.重置用户密码
     * @return
     */
    @Override
    public ResponseResult reloadPassword(Long id) {
        User user = userMapper.selectById(id);
        String encode = passwordEncoder.encode(Const.REGISTER_PASSWORD);
        user.setPassword(encode);
        int i = userMapper.updateById(user);
        if (i != 0){
            return new ResponseResult<>(200,"重置密码成功,重置后密码为:"+Const.REGISTER_PASSWORD);
        }else{
         return  new ResponseResult<>(400,"重置密码失败");
        }
    }

    /**
     * 修改用户密码
     * 1.拿到新密码去查询是否匹配
     * 2.更新密码
     * @param pwdParams
     * @return
     */
    @Override
    public ResponseResult newPassword(PwdParams pwdParams) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = loginUser.getUser().getId();
        User user = userMapper.selectById(id);
        boolean matches = passwordEncoder.matches(pwdParams.getOldPassword(), user.getPassword());
//        如果旧密码和新密码相同
        if (!matches) {
            return new ResponseResult<>(405,"旧密码输入错误");
        }
        user.setPassword(passwordEncoder.encode(pwdParams.getNewPassword()));
        userMapper.updateById(user);
        return new ResponseResult<>(200,"更改密码成功！");
    }

    /**
     * 查询当前登录用户技能
     * 1.查询用户标签信息
     * 2.根据标签信息查询每个标签对应的文章数
     * @return
     */
    private UserVo copy(User user) {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }
}