package com.xiaoyi.birthdayreminder.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftLikeDTO {
   private Integer id;
   private Integer giftId;
   private String userId;
   private LocalDateTime createTime;

}
