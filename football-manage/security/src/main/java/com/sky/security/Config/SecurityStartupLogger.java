package com.sky.security.Config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 启动时打印与 Nacos 相关的 Security / JWT / context-path 配置，便于对照 403 等问题。
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SecurityStartupLogger implements ApplicationRunner {

    private final JwtProperties jwtProperties;
    private final Environment environment;

    @Override
    public void run(ApplicationArguments args) {
        String port = environment.getProperty("server.port", "8081");
        String contextPath = environment.getProperty("server.servlet.context-path", "");
        log.info("[Security-启动] port={} context-path={}（对外登录 URL 一般为 {}/user/login）",
                port, contextPath.isEmpty() ? "(未设置)" : contextPath,
                contextPath.isEmpty() ? "" : contextPath);
        log.info("[Security-启动] sky.jwt enabled={} tokenName={} ttl={} secretKey配置={}",
                jwtProperties.isEnabled(),
                jwtProperties.getTokenName(),
                jwtProperties.getTtl(),
                jwtProperties.getSecretKey() != null && !jwtProperties.getSecretKey().isEmpty() ? "已配置" : "未配置");
    }
}
