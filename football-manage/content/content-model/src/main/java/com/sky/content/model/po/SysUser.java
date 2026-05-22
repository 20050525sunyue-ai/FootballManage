package com.sky.content.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 对应表 {@code sys_user}（登录用户 / RBAC：admin、member）
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class SysUser implements Serializable {

    /** admin */
    public static final String ROLE_ADMIN = "admin";

    /** member */
    public static final String ROLE_MEMBER = "member";

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String username;

    /** BCrypt 等摘要 */
    @TableField("password")
    private String password;

    @TableField("nickname")
    private String nickname;

    @TableField("name")
    private String name;

    @TableField("phone")
    private String phone;

    @TableField("avatar")
    private String avatar;

    /** admin / member */
    @TableField("role")
    private String role;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
