package com.xiaoyi.birthdayreminder.mapper;


import com.xiaoyi.birthdayreminder.pojo.dto.BirthdayItemDTO;
import com.xiaoyi.birthdayreminder.pojo.entity.Birthday;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BirthdayMapper {
    void insert(Birthday birthday);

    @Select("select * from tb_birthday")
    List<Birthday> allList();

    List<BirthdayItemDTO> list(String creator);

    @Select("select * from tb_birthday where id=#{id}")
    Birthday getById(String id);

    void updateById(Birthday birthday);

    @Delete("delete from tb_birthday where id=#{id}")
    void delById(String id);
}
