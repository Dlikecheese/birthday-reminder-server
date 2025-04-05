package com.xiaoyi.birthdayreminder.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gift {
    private Long id;
    private String name;
    private String description;
    private String image;
    private String creator;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer starCount;
    private Integer likeCount;
    private String usageSex;
    private String usageAge;
    private String tag;
}
