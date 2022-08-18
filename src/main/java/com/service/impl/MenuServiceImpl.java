package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.domain.Menu;
import com.service.MenuService;
import com.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
* @author 23340
* @description 针对表【blog_menu(菜单表)】的数据库操作Service实现
* @createDate 2022-08-17 08:24:01
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

}




