package com.sky.content.interceptor.config;

import com.sky.content.interceptor.JwtTokenInterceptor;
import com.sky.security.Config.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 配置类，注册web层相关组件
 */
@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private JwtTokenInterceptor jwtTokenInterceptor;


    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 注册自定义拦截器
     * @param registry
     */
//    protected void addInterceptors(InterceptorRegistry registry) {
//        if (!jwtProperties.isEnabled()) {
//            log.warn("JWT 拦截已关闭（sky.jwt.enabled=false），仅用于开发，上线前务必开启");
//            return;
//        }
//        log.info("开始注册 JWT 拦截器...");
//
//
//        registry.addInterceptor(jwtTokenInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/user/login", "/user/register");
//
//    }

}
