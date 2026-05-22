package com.sky.content.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddAnnouncementDto {

    /*
    ### 请求（Body JSON）

| 英文字段 | 中文含义 | 类型 | 必填 |
|---------|---------|------|------|
| title | 公告标题 | string | 是 |
| content | 公告正文 | string | 是 |
| publisher | 发布人 | string | 是 |
| publishTime | 发布时间（`yyyy-MM-dd HH:mm:ss`） | string | 是 |
     */

    private String title;
    private String content;
    private String publisher;
    private LocalDateTime publishTime;
}
