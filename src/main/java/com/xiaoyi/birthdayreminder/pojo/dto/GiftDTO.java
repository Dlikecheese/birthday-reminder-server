package com.xiaoyi.birthdayreminder.pojo.dto;

import lombok.Data;

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
    private String usageAge;
}
