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
 * 对应表 {@code player}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("player")
public class Player implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("student_id")
    private String studentId;

    @TableField("age")
    private Integer age;

    @TableField("phone")
    private String phone;

    /** 身高(cm) */
    @TableField("height")
    private Integer height;

    /** 体重(kg) */
    @TableField("weight")
    private Integer weight;

    @TableField("avatar")
    private String avatar;

    @TableField("description")
    private String description;

    /** 所属队伍 ID，可空（外键 ON DELETE SET NULL） */
    @TableField("team_id")
    private Long teamId;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
