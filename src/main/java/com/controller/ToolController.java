package com.controller;

import com.service.ToolService;
import com.utils.ResponseResult;
import com.vo.params.ToolParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <h4>blog_admin</h4>
 * <p>工具模块</p>
 *
 * @author : zlz
 * @date : 2022-09-29 17:13
 **/
@RestController
@RequestMapping("api")
public class ToolController {
    @Autowired
    private ToolService toolService;
    @GetMapping("/tool")
    private ResponseResult getTool(){
        return toolService.getTool();
    }
    @PostMapping("/tool/upload")
    private ResponseResult uploadTool(@RequestBody ToolParams toolParams){
          return toolService.uploadTool(toolParams);
    }
    @PostMapping("/tool/delete/{id}")
    private ResponseResult deleteToolById(@PathVariable Long id){
        return toolService.deleteToolById(id);
    }
}
