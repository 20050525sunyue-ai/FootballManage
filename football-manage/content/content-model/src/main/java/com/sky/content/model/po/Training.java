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
 * 对应表 {@code training}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("training")
public class Training implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 训练队伍名称（可与 {@link Team#getTeamName()} 一致） */
    @TableField("team")
    private String team;

    @TableField("training_time")
    private LocalDateTime trainingTime;

    @TableField("location")
    private String location;

    @TableField("content")
    private String content;

    /** 负责人 / 教练 */
    @TableField("coach")
    private String coach;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
