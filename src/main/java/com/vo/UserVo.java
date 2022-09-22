package com.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * <h4>blog_admin</h4>
 * <p></p>
 *
 * @author : zlz
 * @date : 2022-08-24 18:54
 **/
@Data
public class UserVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String  nickName;
    private String avator;
    private String motto;
}
