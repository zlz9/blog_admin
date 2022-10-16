package com.controller;

import com.utils.QiniuUtils;
import com.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;
@Api(tags = "图片上传")
@RestController
@RequestMapping("upload")
public class UploadController {
    @Autowired
    private QiniuUtils qiniuUtils;
    @PostMapping
    /**
     * 上传图片
     */
    @ApiOperation(value = "图片上传")
    public ResponseResult upload(@RequestParam("image") MultipartFile file){
//        原始文件名称，比如aa.png
        String originalFilename = file.getOriginalFilename();
//唯一的文件名称
       String fileName = UUID.randomUUID().toString()+"."+StringUtils.substringAfterLast(originalFilename,".");
        boolean upload = qiniuUtils.upload(file, fileName);
        if (upload) {
            return new ResponseResult<>(200, QiniuUtils.url+fileName);
        }
        return new ResponseResult<>(501,"上传失败");
    }
}
