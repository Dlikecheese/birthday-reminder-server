package com.xiaoyi.birthdayreminder.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

/**
 * 列表页显示的生日一行的信息
 */
@Data
public class BirthdayItemDTO {
    private String id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
}
