package com.xiaoyi.birthdayreminder.service;

import com.xiaoyi.birthdayreminder.pojo.entity.Birthday;

public interface WxSubscribeService {
    String getToken();

    void sendMsg(String token, Birthday birthday);
}
