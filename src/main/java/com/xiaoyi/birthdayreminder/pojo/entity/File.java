package com.xiaoyi.birthdayreminder.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class File {
    private Long id;
    private String imgUrl;
    private String userId;
    private LocalDateTime createTime;
}
