package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.domain.LoginUser;
import com.domain.Menu;
import com.mapper.MenuMapper;
import com.service.MenuService;
import com.utils.ResponseResult;
import com.vo.MenuVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* @author 23340
* @description 针对表【blog_menu(菜单表)】的数据库操作Service实现
* @createDate 2022-08-19 18:57:24
*/
@Slf4j
@Service
@Transactional
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public ResponseResult getUserPerms() {
        /**
         * 获取用户信息
         */
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = loginUser.getUser().getId();
        List<String> permsByUserId = menuMapper.selectPermsByUserId(id);
        if (permsByUserId == null) {
            return new ResponseResult<>(400,"获取权限列表失败");
        }
        return new ResponseResult<>(200, permsByUserId);
    }

    /**
     * 权限列表
     * @return
     */
    @Override
    public ResponseResult getMenuList() {
        /**
         * 获取用户信息
         * 根据用户信息查询menuList
         */
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = loginUser.getUser().getId();
        List<Menu> menuList=menuMapper.selectMenuListById(id);
        log.info("menuList==>{}",menuList);
        /**
         * 1.menu转树状结构
         * 2.menu转dto
         */
//        转树状
       List<Menu> menuTree=buildTreeMenu(menuList);
//       转vo
        List<MenuVo> convert = convert(menuTree);
        log.info("转化后的menu菜单表==>",convert);
        return new ResponseResult(200, convert);
    }

    private List<MenuVo> convert(List<Menu> menuTree) {
        List<MenuVo> menuVoList = new ArrayList<>();
        menuTree.forEach(m -> {
            MenuVo menuVo = new MenuVo();
            menuVo.setId(m.getId());
            menuVo.setIsShow(m.getIsShow());
            menuVo.setName(m.getName());
            menuVo.setComponent(m.getComponent());
            menuVo.setIcon(m.getIcon());
            menuVo.setPath(m.getPath());
            menuVo.setTitle(m.getTitle());
            menuVo.setRedirect(m.getRedirect());
            if (m.getChildren().size()>0){
//                子节点
                menuVo.setChildren(convert(m.getChildren()));
            }else{
                menuVo.setChildren(null);
            }
            menuVoList.add(menuVo);
        });
        return menuVoList;
     }

    private List<Menu> buildTreeMenu(List<Menu> menuList) {
        List<Menu> finalMenus = new ArrayList<>();
//        先各自寻找到各自的孩子
        for (Menu menu : menuList) {
            for (Menu e : menuList){
                 if (menu.getId()==e.getParentId()){
                      menu.getChildren().add(e);
                 }
            }
            //        提取出父节点
            if (menu.getParentId()==0){
                finalMenus.add(menu);
            }
        }
        log.info("树形菜单==>{}",finalMenus);
        return finalMenus;
    }
}




