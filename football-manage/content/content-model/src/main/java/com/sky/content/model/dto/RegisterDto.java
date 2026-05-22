package com.sky.content.model.dto;

import lombok.Data;

@Data
public class RegisterDto {

    /*

| 英文字段 | 中文含义 | 类型 | 必填 |
|---------|---------|------|------|
| username | 登录用户名 | string | 是，全局唯一 |
| password | 登录密码 | string | 是，入库需加密 |
| name | 真实姓名 | string | 是 |
| phone | 手机号码 | string | 是 |
     */
    private String username;
    private String password;
    private String name;
    private String phone;
}
