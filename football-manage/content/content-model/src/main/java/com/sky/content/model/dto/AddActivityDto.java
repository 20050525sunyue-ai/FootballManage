package com.sky.content.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddActivityDto {

    /*
    ### 请求（Body JSON）

| 英文字段 | 中文含义 | 类型 | 必填 |
|---------|---------|------|------|
| title | 活动名称 | string | 是 |
| activityTime | 活动时间（`yyyy-MM-dd HH:mm:ss`） | string | 是 |
| location | 活动地点 | string | 是 |
| content | 活动内容 | string | 是 |
| leader | 负责人 | string | 是 |
     */

    private String title;
    private LocalDateTime activityTime;
    private String location;
    private String content;
    private String leader;
}
