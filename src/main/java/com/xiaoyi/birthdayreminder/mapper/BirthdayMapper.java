package com.xiaoyi.birthdayreminder.mapper;


import com.xiaoyi.birthdayreminder.pojo.dto.BirthdayItemDTO;
import com.xiaoyi.birthdayreminder.pojo.entity.Birthday;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BirthdayMapper {
    void insert(Birthday birthday);

    List<BirthdayItemDTO> list(String creator);
}
