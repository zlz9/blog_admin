package com.vo.params;

import lombok.Data;

/**
 * <h4>blog_admin</h4>
 * <p>密码模块</p>
 *
 * @author : zlz
 * @date : 2022-10-13 18:47
 **/
@Data
public class PwdParams {
    private String oldPassword;
    private String newPassword;
}
