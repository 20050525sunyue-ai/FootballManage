package com.sky.content.interceptor;

import com.sky.base.threadLocal.BaseContext;
import com.sky.security.Config.JwtProperties;
import com.sky.security.Utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理端 JWT 校验。
 */
@Component
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        BaseContext.removeCurrentId();

        String uri = request.getRequestURI();

        // 1. 判断是否开启JWT
        if (!jwtProperties.isEnabled()) {
            log.debug("[JWT] 未启用 sky.jwt.enabled=false，放行 method={} uri={}", request.getMethod(), uri);
            return true;
        }

        // 2. 获取请求头中的token
        String token = request.getHeader(jwtProperties.getTokenName());

        // 3. 判断token是否为空
        if (token == null || token.isEmpty()) {
            log.warn("[JWT] 失败 缺少请求头 {} method={} uri={}", jwtProperties.getTokenName(), request.getMethod(), uri);
            response.setStatus(401);
            return false;
        }

        // 4. 校验token
        try {
            JwtUtil.parseJWT(
                    jwtProperties.getSecretKey(),
                    token
            );
            log.info("[JWT] 校验通过 method={} uri={} userId={}", request.getMethod(), uri, BaseContext.getCurrentId());
        } catch (Exception e) {
            log.warn("[JWT] 失败 解析或验签不通过 method={} uri={} reason={}", request.getMethod(), uri, e.getMessage());
            response.setStatus(401);
            return false;
        }

        // 5. 放行
        return true;
    }

    // 请求结束后清空ThreadLocal
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        BaseContext.removeCurrentId();
    }


}
