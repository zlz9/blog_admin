package com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.domain.WorkImg;
import com.vo.WorkImgVo;

import java.util.List;

/**
* @author 23340
* @description 针对表【blog_work_img】的数据库操作Service
* @createDate 2022-09-22 16:34:10
*/
public interface WorkImgService extends IService<WorkImg> {
    List<WorkImgVo> selectImgByWorkId(Long id);
}
