package com.sky.content.model.dto;

import lombok.Data;

@Data
public class UserInfo {

    /**`userInfo` 内字段：**

            | 英文字段 | 中文含义 | 类型 | 说明 |
            |---------|---------|------|------|
            | id | 用户主键 ID | number | |
            | username | 登录用户名 | string | |
            | nickname | 昵称 | string | 可为空 |
            | name | 真实姓名 | string | 可为空 |
            | avatar | 头像地址 | string | URL，可为空 |
            | createTime | 账号创建时间 | string | |
            | role | 角色 | string | 仅 `admin` 或 `member` |*/

    private Long id;
    private String username;
    private String nickname;
    private String name;
    private String avatar;
    private String createTime;
    private String role;

}
