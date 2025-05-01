package com.xiaoyi.birthdayreminder.mapper;

import com.xiaoyi.birthdayreminder.pojo.entity.File;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    /*
    插入数据
     */
    void insert(File file);

    @Delete("DELETE from tb_upload_file where img_url = #{url}")
    void delByUrl(String url);
}
