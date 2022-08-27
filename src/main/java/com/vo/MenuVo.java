package com.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <h4>blog_admin</h4>
 * <p>Menu Vo层</p>
 *
 * @author : zlz
 * @date : 2022-08-19 21:04
 **/
@Data
public class MenuVo implements Serializable {
    private Long id;
    private String title;
    private String name;
    private String path;
    private String component;
    private String icon;
    private String redirect;
    private List<MenuVo> children = new ArrayList<>();
}
