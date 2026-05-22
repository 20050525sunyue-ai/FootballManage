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
 * 对应表 {@code team}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("team")
public class Team implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("team_name")
    private String teamName;

    @TableField("captain")
    private String captain;

    @TableField("description")
    private String description;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    /** 列表/详情查询中的统计字段，非表字段 */
    @TableField(exist = false)
    private Integer memberCount;
}
