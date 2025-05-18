package com.xiaoyi.birthdayreminder.Enum;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum LunarMonth {
    MONTH_01("01", "正月"),
    MONTH_02("02", "二月"),
    MONTH_03("03", "三月"),
    MONTH_04("04", "四月"),
    MONTH_05("05", "五月"),
    MONTH_06("06", "六月"),
    MONTH_07("07", "七月"),
    MONTH_08("08", "八月"),
    MONTH_09("09", "九月"),
    MONTH_10("10", "十月"),
    MONTH_11("11", "冬月"),
    MONTH_12("12", "腊月");

    private final String value;
    private final String desc;

    LunarMonth(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private static final Map<String, String> VALUE_TO_DESC_MAP = new HashMap<>();

    static {
        for (LunarMonth month : LunarMonth.values()) {
            VALUE_TO_DESC_MAP.put(month.value, month.desc);
        }
    }

    public static Map<String, String> getValueToDescMap() {
        return new HashMap<>(VALUE_TO_DESC_MAP); // 返回副本以保证不可变性
    }
}
