package com.xiaoyi.birthdayreminder.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "birthday.jwt")
@Data
public class JwtProperties {
    /**
     * 微信用户生成jwt令牌相关配置
     */
    private String secretKey;
    private long ttl;
    private String tokenName;

}
