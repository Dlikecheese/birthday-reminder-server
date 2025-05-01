package com.xiaoyi.birthdayreminder.service;
import com.xiaoyi.birthdayreminder.pojo.entity.File;

public interface FileService {
    void insert(File file);

    void deleteByUrl(String url);
}
