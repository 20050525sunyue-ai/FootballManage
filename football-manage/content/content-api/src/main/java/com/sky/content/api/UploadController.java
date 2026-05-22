package com.sky.content.api;

import com.sky.base.model.Result;
import com.sky.content.service.UploadService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class UploadController {
    @Autowired
    private UploadService UploadService;


    // 上传图片
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        // MultipartFile -> byte[]
        byte[] bytes = file.getBytes();

        // 调用 service
        String url = UploadService.upload(bytes);
        return Result.success(url);
    }
}
