package com.xiaoyi.birthdayreminder.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoyi.birthdayreminder.Enum.BirthdayType;
import com.xiaoyi.birthdayreminder.pojo.entity.Birthday;
import com.xiaoyi.birthdayreminder.properties.WeChatProperties;
import com.xiaoyi.birthdayreminder.service.WxSubscribeService;
import com.xiaoyi.birthdayreminder.utils.CommonUtil;
import com.xiaoyi.birthdayreminder.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import static cn.hutool.core.date.LocalDateTimeUtil.parseDate;
import static com.xiaoyi.birthdayreminder.utils.CommonUtil.birthdayInThisYear;
import static com.xiaoyi.birthdayreminder.utils.CommonUtil.formatParam;

@Service
public class WxSubscribeServiceImpl implements WxSubscribeService {
    @Autowired
    private WeChatProperties weChatProperties;

    @Override
    public String getToken() {
        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, String> map = new HashMap<>();
        map.put("appId", weChatProperties.getAppid());
        map.put("secret", weChatProperties.getSecret());
        map.put("grant_type", "client_credential");
        String result = HttpClientUtil.doGet(tokenUrl, map);
        JSONObject jsonObject = JSON.parseObject(result);
        String token = jsonObject.getString("access_token");
        System.out.println(result + token);
        return token;
    }

    @Override
    public void sendMsg(String token, Birthday birthday) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + token;
        HashMap<String, Object> map = new HashMap<>();
        map.put("touser", birthday.getCreator());
        map.put("template_id", weChatProperties.getTemplateId());
        map.put("page", "pages/index/index");

        HashMap<String, Object> data = new HashMap<>();

        String name = birthday.getName().length() > 3 ? birthday.getName().substring(0, 3) : birthday.getName();
        data.put("name1", formatParam(name));

        String solarBirthdayInThisYear = birthdayInThisYear(birthday.getBirthday());
        if (birthday.getBirthdayType().equals(BirthdayType.LUNAR)) {
            solarBirthdayInThisYear = CommonUtil.lunar2Solar(solarBirthdayInThisYear);
        }

        long daysDiff = ChronoUnit.DAYS.between(parseDate(solarBirthdayInThisYear), LocalDate.now());
        String dayDiffStr = daysDiff <= 0 ? "今天" : (daysDiff + "天后");
        data.put("thing2", formatParam(dayDiffStr));
        String dateStr = birthday.getBirthdayType().equals(BirthdayType.LUNAR) ?
                CommonUtil.lunarBirthdayToText(birthday.getBirthday()) : CommonUtil.getMonthDay(birthday.getBirthday());
        data.put("thing6", formatParam(dateStr));
        data.put("thing5", formatParam("别忘了送上生日祝福哦"));
        map.put("data", data);

        String jsonObject = JSON.toJSONString(map);
        HttpUtil.post(url, jsonObject);
    }
}

