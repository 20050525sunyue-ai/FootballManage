package com.sky.content.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateMatchDto {

    /*
    ### 请求（Body JSON，可部分更新；为 null 的字段不修改）

| 英文字段 | 中文含义 | 类型 | 必填 |
|---------|---------|------|------|
| title | 比赛名称 | string | 否 |
| opponent | 对手名称 | string | 否 |
| matchTime | 比赛时间（`yyyy-MM-dd HH:mm:ss`） | string | 否 |
| location | 比赛地点 | string | 否 |
| score | 比分 | string | 否 |
| result | 比赛结果 | string | 否 |
| description | 比赛说明 | string | 否 |
| status | 状态：0 未开始 1 已结束 | number | 否 |
     */

    private String title;
    private String opponent;
    private LocalDateTime matchTime;
    private String location;
    private String score;
    private String result;
    private String description;
    private Integer status;
}
