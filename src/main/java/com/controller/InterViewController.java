package com.controller;

import com.service.InterviewService;
import com.utils.ResponseResult;
import com.vo.params.InterViewParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <h4>blog_admin</h4>
 * <p>面试模块控制器</p>
 *
 * @author : zlz
 * @date : 2022-09-29 11:16
 **/
@RestController
@RequestMapping("api")
public class InterViewController {
    @Autowired
    private InterviewService interviewService;
    @GetMapping("/interview")
    private ResponseResult getInterView(){
        return interviewService.getInterView();
    }
    @PostMapping("/interview/upload")
    private ResponseResult uploadInterView(@RequestBody InterViewParams interViewParams){
        return interviewService.uploadInterView(interViewParams);
    }
}
