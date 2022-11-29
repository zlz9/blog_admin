package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.domain.Menu;

import java.util.List;

/**
* @author 23340
* @description 针对表【blog_menu(菜单表)】的数据库操作Mapper
* @createDate 2022-08-19 18:57:24
* @Entity com.domain.Menu
*/
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectMenuListById(Long id);
}




