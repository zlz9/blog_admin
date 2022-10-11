package com.vo;

import lombok.Data;

import java.util.List;

/**
 * <h4>blog_admin</h4>
 * <p>用户详细信息</p>
 *
 * @author : zlz
 * @date : 2022-10-06 19:06
 **/
@Data
public class UserDetailsVo {
    /**
     * 用户信息
     */
    private UserVo userInfo;
    /**
     * 用户的文章数
     */
    private Integer articleCount;
    /**
     * 作品数
     */
    private Integer workCount;
    /**
     * 出现频率前三的标签
     */
    private List<TagVo> tagVos;
    /**
     * 工具数
     */
    private Integer toolCount;
    /**
     * 用户当前状态
     */
    private boolean status;
    /**
     * 身份
     * 1.超级管理员，2管理员，3会员，4普通用户
     */
    private Long role;
    private String email;
}
