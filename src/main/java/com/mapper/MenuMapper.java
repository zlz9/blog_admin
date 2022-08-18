package com.mapper;

import com.domain.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 23340
* @description 针对表【blog_menu(菜单表)】的数据库操作Mapper
* @createDate 2022-08-17 08:24:01
* @Entity com.domain.Menu
*/
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

}




