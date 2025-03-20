package com.xiaoyi.birthdayreminder.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
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
    private LocalDate birthday;

    // 地址
    private String address;

    //手机号
    private String phone;

    //性别 0 女 1 男
    private String sex;

    //头像
    private String avatar;

    //注册时间
    private LocalDateTime createTime;
}
