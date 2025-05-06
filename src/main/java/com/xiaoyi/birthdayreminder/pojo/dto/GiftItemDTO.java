package com.xiaoyi.birthdayreminder.pojo.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GiftItemDTO {
    private Integer id;
    private String name;
    private String description;
    private String image;
    private String creatorName;
    private String creator;
    private Integer favoriteCount;
    private Integer collectCount;
    private Boolean isMine;
    // 是否收藏
    private Boolean isFavoritedByUser;
    // 是否喜欢
    private Boolean isCollectedByUser;
    private String creatorAvatar;
    private String usageAge;
    private String usageSex;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updateTime;
}
