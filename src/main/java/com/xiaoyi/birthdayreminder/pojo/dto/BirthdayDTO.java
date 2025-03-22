package com.xiaoyi.birthdayreminder.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String[] remindTime;
    private String sex;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updateTime;
    private String address;
    private String tag;
    private String phone;
    private String comment;
    private String creator;

    @JsonSetter(nulls = Nulls.SKIP)
    private Relation relation;

    public void setRelation(String relation) {
        if (relation == null || relation.isEmpty()) {
            this.relation = null; // 或者设置为默认值
        } else {
            this.relation = Relation.valueOf(relation.toUpperCase());
        }
    }
}
