package com.xiaoyi.birthdayreminder.controller;

import com.xiaoyi.birthdayreminder.context.BaseContext;
import com.xiaoyi.birthdayreminder.pojo.Result;
import com.xiaoyi.birthdayreminder.pojo.entity.File;
import com.xiaoyi.birthdayreminder.service.FileService;
import com.xiaoyi.birthdayreminder.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;


@RequestMapping("/common")
@RestController
@Slf4j
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){
        log.info("文件上传:{}",file);

        try {
            String originalFileName = file.getOriginalFilename();
          String extension=  originalFileName.substring(originalFileName.lastIndexOf("."));
            String objectName = UUID.randomUUID()+extension;

          String filePath =  aliOssUtil.upload(file.getBytes(),objectName);
            File uploadFile= new File();
            uploadFile.setCreateTime(LocalDateTime.now());
            uploadFile.setUserId(BaseContext.getCurrentId());
            uploadFile.setImgUrl(filePath);
            fileService.insert(uploadFile);

          return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：",e);
        }
        return null;
    }

    @GetMapping("/test")
    public Result<String> test(){
        log.info("test");
        return Result.success("测试成功");
    }
}
