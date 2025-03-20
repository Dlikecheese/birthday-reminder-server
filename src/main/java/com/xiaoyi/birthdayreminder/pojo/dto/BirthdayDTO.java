package com.xiaoyi.birthdayreminder.pojo.dto;

import com.xiaoyi.birthdayreminder.Enum.Relation;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户登录
 */
@Data
public class BirthdayDTO {
    private String id;
    private String name;
    private LocalDate birthday;
    private String[] remindTime;
    private String sex;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Relation relation;
    private String address;
    private String tag;
    private String phone;
    private String comment;
    private String creator;
}
