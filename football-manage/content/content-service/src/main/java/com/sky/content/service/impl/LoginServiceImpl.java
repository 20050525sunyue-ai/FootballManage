package com.sky.content.service.impl;

import com.sky.base.constant.JwtClaimsConstant;
import com.sky.base.utils.RedisUtil;
import com.sky.content.model.dto.LoginDTO;
import com.sky.content.model.dto.RegisterDto;
import com.sky.content.model.po.SysUser;
import com.sky.content.mapper.LoginMapper;
import com.sky.security.Config.JwtProperties;
import com.sky.content.service.LoginService;
import com.sky.security.Utils.JwtUtil;
import com.sky.content.model.dto.LoginVo;
import com.sky.content.model.dto.UserInfo;
import com.sky.security.entity.LoginUser;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private RedisUtil redisUtil;


    // 登录校验
    @Override
    public LoginVo login(LoginDTO loginDTO) {
        log.info("[登录] 开始 username={}", loginDTO.getUsername());

        // 如果下面这一步执行成功，没有抛异常，就说明Spring Security 已经认证成功了，包括：
        //✅ 用户存在✅ 密码正确✅ UserDetailsService 已执行成功✅ PasswordEncoder.matches 已校验成功
        //✅ DaoAuthenticationProvider 已认证成功✅ Authentication 已创建成功
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        // 拿到authenticate返回的信息，包括name,passwork和权限，这里必须转成User，否则无法使用getUsername等方法
        LoginUser authResult = (LoginUser)authenticate.getPrincipal();
        // 根据用户名查询用户信息
        SysUser sysUser = loginMapper.selectUserInfo(authResult.getUsername());

        LoginUser loginUser = new LoginUser(sysUser, authResult.getPermissions());

        // 将用户信息和权限信息添加进redis，userId作为key，用户信息作为value
        redisUtil.setObject("login:"+loginUser.getSysUser().getId().toString(), loginUser);


        /*     ================原始认证方法================
                String username = loginDTO.getUsername();
//        String password = loginDTO.getPassword();
//        // 2. 判断用户名密码是否为空
//        if (username == null || username.length() == 0){
//            log.warn("[登录] 失败 用户名为空");
//            throw new RuntimeException("用户名不能为空");
//        }
//        if (password == null || password.length() == 0){
//            log.warn("[登录] 失败 密码为空 username={}", username);
//            throw new RuntimeException("密码不能为空");
//        }
//        SysUser sysUser = loginMapper.selectUserInfo(username);
//        if (sysUser == null){
//            log.warn("[登录] 失败 用户不存在 username={}", username);
//            throw new RuntimeException("用户不存在");
//        }
//
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        if (!encoder.matches(
//                loginDTO.getPassword(), sysUser.getPassword())){
//            log.warn("[登录] 失败 用户名或密码错误 username={}", username);
//            throw new RuntimeException("用户名或密码错误");
        }

 */

                // 3. 创建JWT
        LoginVo loginVo = new LoginVo();
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(sysUser, userInfo);
        if (sysUser.getCreateTime() != null) {
            userInfo.setCreateTime(sysUser.getCreateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }


        loginVo.setUserInfo(userInfo);
        Map<String,Object> UserInfo = new HashMap<>();
        UserInfo.put(JwtClaimsConstant.USER_ID, userInfo.getId());
        UserInfo.put(JwtClaimsConstant.USER_NAME, userInfo.getUsername());
        String token = JwtUtil.createJWT(jwtProperties.getSecretKey(), jwtProperties.getTtl(), UserInfo);
        loginVo.setToken(token);
        log.info("[登录] 成功 username={} userId={} 已签发 JWT（长度={}，过期毫秒={}）",
                userInfo.getUsername(), userInfo.getId(), token.length(), jwtProperties.getTtl());

        return loginVo;
    }

    @Override
    public Boolean register(RegisterDto registerDto) {
        // 对密码加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(registerDto.getPassword());
        registerDto.setPassword(encodePassword);
        return loginMapper.register(registerDto) > 0;
    }

    @Override
    public void logout() {
        // 根据SecurityContextHolder获取用户信息，然后删除redis中的用户信息
        SysUser sysUser  =(SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        redisUtil.delete("login:"+sysUser.getId().toString());
        log.info("[登出] 成功 username={} userId={}", sysUser.getUsername(), sysUser.getId());
    }


}
