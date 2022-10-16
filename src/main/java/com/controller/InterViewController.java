package com.controller;

import com.service.InterviewService;
import com.utils.ResponseResult;
import com.vo.params.InterViewParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <h4>blog_admin</h4>
 * <p>面试模块控制器</p>
 *
 * @author : zlz
 * @date : 2022-09-29 11:16
 **/
@Api(tags = "面试模块")
@RestController
@RequestMapping("api")
public class InterViewController {
    @Autowired
    private InterviewService interviewService;

    @ApiOperation(value = "获取面试")
    @GetMapping("/interview")
    private ResponseResult getInterView(){
        return interviewService.getInterView();
    }

    @ApiOperation(value = "上传面试")
    @PostMapping("/interview/upload")
    private ResponseResult uploadInterView(@RequestBody InterViewParams interViewParams){
        return interviewService.uploadInterView(interViewParams);
    }

    @ApiOperation(value = "删除面试")
    @GetMapping("/interview/del/{id}")
    private ResponseResult delInterview(@PathVariable Long id){
        return interviewService.delInterview(id);
    }
}
