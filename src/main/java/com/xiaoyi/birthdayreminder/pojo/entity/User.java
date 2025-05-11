package com.xiaoyi.birthdayreminder.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaoyi.birthdayreminder.Enum.BirthdayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;

    //微信用户唯一标识
    private String openid;

    //姓名
    private String name;

    // 生日
    private String birthday;

    private BirthdayType birthdayType;

    // 地址
    private String address;

    //手机号
    private String phone;

    //性别
    private String sex;

    //头像
    private String avatar;

    //注册时间
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;
}
