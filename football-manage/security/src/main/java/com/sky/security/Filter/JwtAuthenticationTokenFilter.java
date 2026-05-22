package com.sky.security.Filter;

import com.sky.base.utils.RedisUtil;
import com.sky.content.model.po.SysUser;
import com.sky.security.Config.JwtProperties;
import com.sky.security.Utils.JwtUtil;
import com.sky.security.entity.LoginUser;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1.获取token
        String token = request.getHeader(jwtProperties.getTokenName());
        if (token == null || token.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }
        // 2.解析token并获取userid
        Claims claims = JwtUtil.parseJWT(jwtProperties.getSecretKey(), token);

        Object userId = claims.get("UserId");
        // 3.根据key从redis中获取用户信息
        String key = "login:" + userId;

        LoginUser loginUser = redisUtil.getObject(key, LoginUser.class);
        if (loginUser.getSysUser() == null) {
            throw new RuntimeException("用户未登录");
        }

        // 4.将用户信息,权限信息存入SecurityContextHolder   必须要用三个参数的
        // TODO 获取权限信息封装
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(loginUser.getSysUser(), null, loginUser.getAuthorities()));

        // 放行
        filterChain.doFilter(request, response);
    }
}
