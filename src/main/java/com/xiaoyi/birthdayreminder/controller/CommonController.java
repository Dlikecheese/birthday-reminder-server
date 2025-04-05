package com.xiaoyi.birthdayreminder.controller;

import com.xiaoyi.birthdayreminder.pojo.Result;
import com.xiaoyi.birthdayreminder.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@RequestMapping("/common")
@RestController
@Slf4j
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){
        log.info("文件上传:{}",file);

        try {
            String originalFileName = file.getOriginalFilename();
          String extension=  originalFileName.substring(originalFileName.lastIndexOf("."));
            String objectName = UUID.randomUUID().toString()+extension;

          String filePath =   aliOssUtil.upload(file.getBytes(),objectName);
          return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败:{}",e);
        }
        return null;
    }
}
