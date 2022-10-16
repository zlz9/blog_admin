package com.service;

import com.domain.Interview;
import com.baomidou.mybatisplus.extension.service.IService;
import com.utils.ResponseResult;
import com.vo.params.InterViewParams;

/**
* @author 23340
* @description 针对表【blog_interview】的数据库操作Service
* @createDate 2022-09-29 11:15:33
*/
public interface InterviewService extends IService<Interview> {

    ResponseResult getInterView();

    ResponseResult uploadInterView(InterViewParams interViewParams);

    ResponseResult delInterview(Long id);
}
