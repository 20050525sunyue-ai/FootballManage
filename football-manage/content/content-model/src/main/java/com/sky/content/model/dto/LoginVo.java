package com.sky.content.model.dto;


import lombok.Data;

@Data
public class LoginVo{

    // token
    private String token;
    // userInfo
    private UserInfo userInfo;
}
