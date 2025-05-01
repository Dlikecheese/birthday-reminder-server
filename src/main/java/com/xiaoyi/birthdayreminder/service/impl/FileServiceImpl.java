package com.xiaoyi.birthdayreminder.service.impl;
import com.xiaoyi.birthdayreminder.mapper.FileMapper;
import com.xiaoyi.birthdayreminder.pojo.entity.File;
import com.xiaoyi.birthdayreminder.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public void insert(File file) {
        fileMapper.insert(file);
    }

    @Override
    public void deleteByUrl(String url) {
        fileMapper.delByUrl(url);
    }
}
