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
 * 对应表 {@code match_info}（比赛）
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("match_info")
public class MatchInfo implements Serializable {

    /** 未开始 */
    public static final int STATUS_NOT_STARTED = 0;
    /** 已结束 */
    public static final int STATUS_FINISHED = 1;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("title")
    private String title;

    @TableField("opponent")
    private String opponent;

    @TableField("match_time")
    private LocalDateTime matchTime;

    @TableField("location")
    private String location;

    @TableField("score")
    private String score;

    /** 胜/平/负等 */
    @TableField("result")
    private String result;

    @TableField("description")
    private String description;

    /** 0 未开始 1 已结束 */
    @TableField("status")
    private Integer status;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
