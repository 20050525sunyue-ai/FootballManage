package com.sky.content.service.impl;

import com.sky.content.mapper.LoginMapper;
import com.sky.content.model.po.SysUser;
import com.sky.security.entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/*
UserDetailsServiceImpl是Spring Security 内部认证流程的一部分
你自己不会主动调用它。而是：
authenticationManager.authenticate(...)
        ↓
ProviderManager
        ↓
DaoAuthenticationProvider
        ↓
UserDetailsService.loadUserByUsername()

自动调用。



 */

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    LoginMapper loginMapper;


    List<String> list = new ArrayList<>(Arrays.asList("ROLE_ADMIN", "ROLE_USER","ROLE_TEST"));


    // DaoAuthenticationProvider自动来调用loadUserByUsername获取用户信息，然后会立刻进行密码校验

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("[登录校验] loadUserByUsername username={}", username);
        SysUser sysUser = loginMapper.selectUserInfo(username);
        if (sysUser == null) {
            log.warn("[登录校验] 用户不存在 username={}", username);
            throw new UsernameNotFoundException("用户不存在");
        }
        log.info("[登录校验] 用户已加载 username={} userId={}", username, sysUser.getId());
        return new LoginUser(sysUser, list);
    }

    /* 返回new User 之后
    DaoAuthenticationProvider 会立刻做：
            passwordEncoder.matches(
                        用户输入的原始密码,
                        数据库里的BCrypt密文
                            )
*/

}
