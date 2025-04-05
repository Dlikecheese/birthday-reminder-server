package com.xiaoyi.birthdayreminder.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageBean {
    private Long total;
    private List rows;
}
