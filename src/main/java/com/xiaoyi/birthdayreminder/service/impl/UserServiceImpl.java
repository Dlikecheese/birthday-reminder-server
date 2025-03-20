package com.xiaoyi.birthdayreminder.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoyi.birthdayreminder.constant.MessageConstant;
import com.xiaoyi.birthdayreminder.exception.LoginFailedException;
import com.xiaoyi.birthdayreminder.mapper.UserMapper;
import com.xiaoyi.birthdayreminder.pojo.dto.UserLoginDTO;
import com.xiaoyi.birthdayreminder.pojo.entity.User;
import com.xiaoyi.birthdayreminder.properties.WeChatProperties;
import com.xiaoyi.birthdayreminder.service.UserService;
import com.xiaoyi.birthdayreminder.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    public static  final String WX_LOGIN="https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private WeChatProperties weChatProperties;

    @Autowired
    private UserMapper userMapper;
    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    public User wxLogin(UserLoginDTO userLoginDTO) {
        // 调用微信接口，获得当前用户的openId
        String openId = getOpenId(userLoginDTO);
        // 判断openId是否为空，如果为空，登录失败，抛出业务异常

        if(openId == null){
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }
        // 判断当前用户是否为新用户
       User user =  userMapper.getByOpenId(openId);

        // 如果是新用户，自动完成注册
        if(user ==null){
           user =  User.builder()
                   .id(openId)
                    .openid(openId)
                    .createTime(LocalDateTime.now())
                    .build();

           userMapper.insert(user);
        }

        // 返回用户对象
        return user;
    }

    private String getOpenId(UserLoginDTO userLoginDTO) {
        Map<String,String> map =new HashMap<>();
        map.put("appId",weChatProperties.getAppid());
        map.put("secret",weChatProperties.getSecret());
        map.put("js_code", userLoginDTO.getCode());
        map.put("grant_type","authorization_code");
        String json  = HttpClientUtil.doGet(WX_LOGIN,map);
        JSONObject jsonObject = JSON.parseObject(json);
        String openId= jsonObject.getString("openid");
        return openId;
    }

    @Override
    public void updateById(String id, User user) {
        userMapper.updateById(id,user);
    }
}
