package com.xiaoyi.birthdayreminder.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 前端传输的礼物信息
 */
@Data
public class GiftDTO {
    private Integer id;
    private String name;
    private String description;
    private String image;
    private String usageSex;
    private String[] usageAge;
    private Boolean isMine;
    private String creator;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updateTime;
}
