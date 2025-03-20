package com.xiaoyi.birthdayreminder.service;

import com.xiaoyi.birthdayreminder.pojo.dto.UserLoginDTO;
import com.xiaoyi.birthdayreminder.pojo.entity.User;

public interface UserService {
    User wxLogin(UserLoginDTO userLoginDTO);

    void updateById(String id, User user);
}
