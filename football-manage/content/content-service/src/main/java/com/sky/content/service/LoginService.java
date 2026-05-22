package com.sky.content.service;

import com.sky.content.model.dto.LoginDTO;
import com.sky.content.model.dto.RegisterDto;
import com.sky.base.model.Result;
import com.sky.content.model.dto.LoginVo;

public interface LoginService {
    // 登录校验
    LoginVo login(LoginDTO loginDTO);

    // 注册
    Boolean register(RegisterDto registerDto);

    void logout();
}
