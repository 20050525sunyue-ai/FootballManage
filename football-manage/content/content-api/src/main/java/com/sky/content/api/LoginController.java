package com.sky.content.api;

import com.sky.content.model.dto.LoginDTO;
import com.sky.content.model.dto.RegisterDto;
import com.sky.base.model.Result;
import com.sky.content.service.LoginService;
import com.sky.content.model.dto.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

/**
 * 登录认证（骨架：无数据库账号表时使用固定账号演示 JWT；足球队业务开发后可改为 Mapper 校验）
 */
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    // 登录

    @PostMapping("/user/login")
    // TODO
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Result<LoginVo> login(@RequestBody LoginDTO loginDTO) {
        log.info("[登录] 收到请求 username={}", loginDTO != null ? loginDTO.getUsername() : null);
        return Result.success(loginService.login(loginDTO));
    }


    // 注册
    @PostMapping("/user/register")
    public Result<Boolean> register(@RequestBody RegisterDto registerDto) {
        Boolean register = loginService.register(registerDto);
        return Result.success(register);
    }


    @PostMapping("user/logout")
    public Result<Boolean> logout() {
        loginService.logout();
        return Result.success(true);
    }
}
