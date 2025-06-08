package com.xiaoyi.birthdayreminder.schedule;

import com.xiaoyi.birthdayreminder.pojo.entity.Birthday;
import com.xiaoyi.birthdayreminder.service.BirthdayService;
import com.xiaoyi.birthdayreminder.service.WxSubscribeService;
import com.xiaoyi.birthdayreminder.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubscriptionScheduler {

    @Autowired
    BirthdayService birthdayService;

    @Autowired
    WxSubscribeService wxSubscribeService;

    @Scheduled(cron = "0 30 11 * * ?") // 每天上午9点执行
    public void sendDailySubscription() {
        // 1. 查询需要发送订阅消息的用户列表
        List<Birthday> allBirthdayList = birthdayService.allList();
        List<Birthday> remindBirthdayList = CommonUtil.getReminderDate(allBirthdayList);

        String token = wxSubscribeService.getToken();

        for (Birthday birthday : remindBirthdayList) {
            wxSubscribeService.sendMsg(token, birthday);
        }
    }
}
