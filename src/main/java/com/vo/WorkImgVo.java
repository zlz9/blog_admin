package com.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * <h4>blog_admin</h4>
 * <p>作品图片资源vo</p>
 *
 * @author : zlz
 * @date : 2022-09-22 16:59
 **/
@Data
public class WorkImgVo {
    /**
     * 图片id
     */
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 图片资源
     */
    private String url;
}
