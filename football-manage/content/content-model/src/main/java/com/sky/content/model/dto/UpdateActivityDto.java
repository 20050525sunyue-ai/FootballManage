package com.sky.content.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateActivityDto {

    /*
    ### 请求（Body JSON，可部分更新；为 null 的字段不修改）

| 英文字段 | 中文含义 | 类型 | 必填 |
|---------|---------|------|------|
| title | 活动名称 | string | 否 |
| activityTime | 活动时间（`yyyy-MM-dd HH:mm:ss`） | string | 否 |
| location | 活动地点 | string | 否 |
| content | 活动内容 | string | 否 |
| leader | 负责人 | string | 否 |
     */

    private String title;
    private LocalDateTime activityTime;
    private String location;
    private String content;
    private String leader;
}
