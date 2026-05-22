package com.sky.security.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sky.jwt")
@Data
public class JwtProperties {

    /**
     * 是否启用 JWT 拦截校验。开发环境可设为 false 关闭；上线务必为 true。
     */
    private boolean enabled = true;


    private String SecretKey;
    private long Ttl;
    private String TokenName;

}
