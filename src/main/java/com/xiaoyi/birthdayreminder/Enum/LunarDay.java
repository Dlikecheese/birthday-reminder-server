package com.xiaoyi.birthdayreminder.Enum;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum LunarDay {
    DAY_01("01", "初一"),
    DAY_02("02", "初二"),
    DAY_03("03", "初三"),
    DAY_04("04", "初四"),
    DAY_05("05", "初五"),
    DAY_06("06", "初六"),
    DAY_07("07", "初七"),
    DAY_08("08", "初八"),
    DAY_09("09", "初九"),
    DAY_10("10", "初十"),
    DAY_11("11", "十一"),
    DAY_12("12", "十二"),
    DAY_13("13", "十三"),
    DAY_14("14", "十四"),
    DAY_15("15", "十五"),
    DAY_16("16", "十六"),
    DAY_17("17", "十七"),
    DAY_18("18", "十八"),
    DAY_19("19", "十九"),
    DAY_20("20", "二十"),
    DAY_21("21", "廿一"),
    DAY_22("22", "廿二"),
    DAY_23("23", "廿三"),
    DAY_24("24", "廿四"),
    DAY_25("25", "廿五"),
    DAY_26("26", "廿六"),
    DAY_27("27", "廿七"),
    DAY_28("28", "廿八"),
    DAY_29("29", "廿九"),
    DAY_30("30", "三十");

    private final String value;
    private final String desc;

    LunarDay(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private static final Map<String, String> VALUE_TO_DESC_MAP = new HashMap<>();

    static {
        for (LunarDay day : LunarDay.values()) {
            VALUE_TO_DESC_MAP.put(day.value, day.desc);
        }
    }

    public static Map<String, String> getValueToDescMap() {
        return new HashMap<>(VALUE_TO_DESC_MAP); // 返回副本以保证不可变性
    }
}

