package com.xiaoyi.birthdayreminder.controller;

import com.xiaoyi.birthdayreminder.constant.JwtClaimsConstant;
import com.xiaoyi.birthdayreminder.context.BaseContext;
import com.xiaoyi.birthdayreminder.pojo.Result;
import com.xiaoyi.birthdayreminder.pojo.dto.UserLoginDTO;
import com.xiaoyi.birthdayreminder.pojo.entity.User;
import com.xiaoyi.birthdayreminder.pojo.vo.UserLoginVO;
import com.xiaoyi.birthdayreminder.properties.JwtProperties;
import com.xiaoyi.birthdayreminder.service.UserService;
import com.xiaoyi.birthdayreminder.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO){
        log.info("微信用户登录");
        User user = userService.wxLogin(userLoginDTO);

//        jwt令牌
        Map<String,Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID,user.getId());
       String token =  JwtUtil.createJWT(jwtProperties.getSecretKey(),jwtProperties.getTtl(),claims);


     UserLoginVO userLoginVO =   UserLoginVO.builder()
               .id(user.getId())
               .openid(user.getOpenid())
               .token(token)
                .name(user.getName())
                .avatar(user.getAvatar())
               .build();
        return Result.success(userLoginVO);
    }

    @GetMapping("/logout")
    public Result<Void> logout(){
        BaseContext.removeCurrentId();
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable String id, @RequestBody User user){
        userService.updateById(id,user);
        return Result.success();
    }
}
