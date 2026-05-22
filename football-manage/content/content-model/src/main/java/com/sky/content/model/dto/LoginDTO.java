package com.sky.content.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 管理端登录（骨架示例：后续可替换为数据库校验）
 */
@Data
public class LoginDTO implements Serializable {

    private String username;

    private String password;

}
