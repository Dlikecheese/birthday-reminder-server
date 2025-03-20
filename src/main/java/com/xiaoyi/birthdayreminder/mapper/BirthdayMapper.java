package com.xiaoyi.birthdayreminder.mapper;


import com.xiaoyi.birthdayreminder.pojo.entity.Birthday;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BirthdayMapper {
    void insert(Birthday birthday);
}
