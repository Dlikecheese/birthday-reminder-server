package com.xiaoyi.birthdayreminder.utils;

import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateUtil;
import com.xiaoyi.birthdayreminder.Enum.BirthdayType;
import com.xiaoyi.birthdayreminder.Enum.LunarDay;
import com.xiaoyi.birthdayreminder.Enum.LunarMonth;
import com.xiaoyi.birthdayreminder.Enum.RemindTime;
import com.xiaoyi.birthdayreminder.pojo.entity.Birthday;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CommonUtil {
    public static HashMap<String, String> formatParam(String value) {
        HashMap<String, String> data = new HashMap<>();
        data.put("value", value);
        return data;
    }

    /**
     * 农历转换为公历
     *
     * @param lunarDateStr yyyy-MM-dd 格式
     */
    public static String lunar2Solar(String lunarDateStr) {
        String[] parts = lunarDateStr.split("-");
        int lunarYear = Integer.parseInt(parts[0]);
        int lunarMonth = Integer.parseInt(parts[1]);
        int lunarDay = Integer.parseInt(parts[2]);

        ChineseDate chineseDate = new ChineseDate(lunarYear, lunarMonth, lunarDay);
        Date solarDate = chineseDate.getGregorianDate();

        // 输出结果（格式化为yyyy-MM-dd）
        return DateUtil.format(solarDate, "yyyy-MM-dd");
    }

    /**
     * 获取需要提醒的生日
     */
    public static List<Birthday> getReminderDate(List<Birthday> birthdayList) {
        String todayStr = formatDate(LocalDate.now());
        List<Birthday> remindBirthdayList = new ArrayList<>();

        birthdayList.forEach((birthday) -> {
            String solarBirthday = birthday.getBirthday();
            if (birthday.getBirthdayType().equals(BirthdayType.LUNAR)) {
                solarBirthday = CommonUtil.lunar2Solar(birthdayInThisYear(solarBirthday));
            }

            if (getMonthDay(todayStr).equals(getMonthDay((solarBirthday)))) {
                remindBirthdayList.add(birthday);
                return;
            }

            LocalDate givenDate = LocalDate.parse(solarBirthday, DateTimeFormatter.ISO_LOCAL_DATE);
            String[] remindTimes = birthday.getRemindTime().split(",");

            for (String remindTime : remindTimes) {
                int code = RemindTime.valueOf(remindTime).getCode();
                LocalDate remindDate = givenDate.minusDays(code);
                String remindDateStr = formatDate(remindDate);

                if (getMonthDay(todayStr).equals(getMonthDay((remindDateStr)))) {
                    remindBirthdayList.add(birthday);
                    break;
                }
            }
        });

        return remindBirthdayList;
    }

    /**
     * 格式化日期为yyyy-MM-dd格式
     */
    public static String formatDate(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(formatter);
    }

    /**
     * 获取月日信息
     *
     * @param date 格式为yyyy-MM-dd
     */
    public static String getMonthDay(String date) {
        return date.substring(date.indexOf("-") + 1);
    }

    /**
     * 获取月日信息
     *
     * @param date 格式为yyyy-MM-dd
     */
    public static String birthdayInThisYear(String date) {
        return Year.now().getValue() + "-" + getMonthDay((date));
    }

    /**
     * 获得农历生日描述
     *
     * @param birthday yyyy-MM-dd
     */
    public static String lunarBirthdayToText(String birthday) {
        String[] date = birthday.split("-");
        String month = date[1];
        String day = date[2];
        return "农历" + LunarMonth.getValueToDescMap().get(month) + LunarDay.getValueToDescMap().get(day);
    }
}
