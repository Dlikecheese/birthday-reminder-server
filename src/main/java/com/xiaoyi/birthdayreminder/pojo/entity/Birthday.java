package com.xiaoyi.birthdayreminder.pojo.entity;

import com.xiaoyi.birthdayreminder.Enum.Relation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Birthday {
    private String id;
    private String name;
    private LocalDate birthday;
    private String remindTime;
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
