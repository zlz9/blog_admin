package com.controller;

import com.service.WorkService;
import com.utils.ResponseResult;
import com.vo.params.WorkParams;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <h4>blog_admin</h4>
 * <p>作品模块</p>
 *
 * @author : zlz
 * @date : 2022-09-22 16:35
 **/
@Api(tags = "作品模块")
@RestController
@RequestMapping("api")
public class WorkController {
    @Autowired
    private WorkService workService;


    @GetMapping("author/works")
    private ResponseResult getWork(){
      return workService.getWork();
    }


    @GetMapping("/works/detail/{id}")
    private ResponseResult getWorkInfo(@PathVariable Long id){
        return workService.getWorkInfo(id);
    }

    @PostMapping("works/update")
    private  ResponseResult updateWork(@RequestBody WorkParams workParams){
        return workService.updateWork(workParams);
    }

    @PostMapping("work/delete/{id}")
    private ResponseResult deleteWork(@PathVariable Long id){
        return workService.deleteWork(id);
    }
}