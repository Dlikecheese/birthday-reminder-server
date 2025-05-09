package com.xiaoyi.birthdayreminder.pojo.dto;

import com.xiaoyi.birthdayreminder.Enum.BirthdayType;
import lombok.Data;

/**
 * 列表页显示的生日一行的信息
 */
@Data
public class BirthdayItemDTO {
    private String id;
    private String name;
    private String birthday;
    private BirthdayType birthdayType;
}
