package com.xiaoyi.birthdayreminder.mapper;

import com.xiaoyi.birthdayreminder.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from tb_user where openid=#{openid}")
    User getByOpenId(String openid);

    /*
    插入数据
     */
    void insert(User user);

    void updateById(@Param("id") String id,@Param("user") User user);
}
