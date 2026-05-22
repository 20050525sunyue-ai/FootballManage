package com.sky.content.service.impl;

import com.sky.content.service.UploadService;
import com.sky.base.utils.AliOssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private AliOssUtil aliOssUtil;

    @Override
    public String upload(byte[] bytes) {

        String objectName =
                "player/avatar/"
                        + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                        + "/"
                        + UUID.randomUUID()
                        + ".png";
        return aliOssUtil.upload(bytes, objectName);
    }
}
