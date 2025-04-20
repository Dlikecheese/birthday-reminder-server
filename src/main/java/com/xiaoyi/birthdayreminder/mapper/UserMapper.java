package com.xiaoyi.birthdayreminder.mapper;

import com.xiaoyi.birthdayreminder.pojo.dto.FeedbackDTO;
import com.xiaoyi.birthdayreminder.pojo.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from tb_user where openid=#{openid}")
    User getByOpenId(String openid);

    /*
    插入数据
     */
    void insert(User user);

    void updateById(@Param("id") String id,@Param("user") User user);

    void feedback(FeedbackDTO feedbackDTO);
}
