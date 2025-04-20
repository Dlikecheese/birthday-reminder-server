package com.xiaoyi.birthdayreminder.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeedbackDTO {
    private String id;
    private String content;
    private String user;
    private LocalDateTime createTime;
}
