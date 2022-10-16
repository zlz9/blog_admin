package com.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

/**
 * <h4>blog_admin</h4>
 * <p>作品vo层</p>
 *
 * @author : zlz
 * @date : 2022-09-22 16:51
 **/
@Data
public class WorkVo {
    /**
     * id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 作品名
     */
    private String name;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 作品描述
     */
    private String description;

    /**
     * 作者id
     */
    private Long authorId;

    /**
     * 1正常，0异常
     */
    private Boolean status;

    /**
     * github(链接)
     */
    private String linkGithub;

    /**
     * gitee(链接)
     */
    private String linkGitee;

    /**
     * 线上预览
     */
    private String preview;

    /**
     * 产品定位
     */
    private String position;
    private List<WorkImgVo> img;
}
