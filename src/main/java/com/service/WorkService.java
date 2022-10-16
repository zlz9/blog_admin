package com.service;

import com.domain.Work;
import com.baomidou.mybatisplus.extension.service.IService;
import com.utils.ResponseResult;
import com.vo.params.RootPage;
import com.vo.params.WorkParams;
/**
* @author 23340
* @description 针对表【blog_work】的数据库操作Service
* @createDate 2022-09-22 16:34:10
*/
public interface WorkService extends IService<Work> {

    ResponseResult getWork(RootPage rootPage);

    ResponseResult getWorkInfo(Long id);

    ResponseResult updateWork(WorkParams workParams);

    ResponseResult deleteWork(Long id);

    Integer getWorkCountById(Long id);

    ResponseResult getWorkCount();
}
