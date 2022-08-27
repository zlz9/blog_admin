package com.service;

import com.domain.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.utils.ResponseResult;

/**
* @author 23340
* @description 针对表【blog_menu(菜单表)】的数据库操作Service
* @createDate 2022-08-19 18:57:24
*/
public interface MenuService extends IService<Menu> {

    ResponseResult getUserPerms();

    ResponseResult getMenuList();
}
