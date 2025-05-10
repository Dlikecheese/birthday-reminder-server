package com.xiaoyi.birthdayreminder.Enum;

import lombok.Getter;

@Getter
public enum RemindTime {
    ONE_DAY(1),
    TWO_DAY(2),
    THREE_DAY(3),
    FOUR_DAY(4),
    ONE_WEEK(7),
    ONE_MONTH(30);

    private final int code;

    RemindTime(int code) {
        this.code = code;
    }

}
