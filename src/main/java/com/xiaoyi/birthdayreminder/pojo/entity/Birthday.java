package com.xiaoyi.birthdayreminder.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String remindTime;
    private String sex;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updateTime;
    private Relation relation;
    private String address;
    private String tag;
    private String phone;
    private String comment;
    private String creator;
}
