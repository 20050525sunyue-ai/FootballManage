package com.sky.content.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateAnnouncementDto {

    /*
    ### 请求（Body JSON，可部分更新；为 null 的字段不修改）

| 英文字段 | 中文含义 | 类型 | 必填 |
|---------|---------|------|------|
| title | 公告标题 | string | 否 |
| content | 公告正文 | string | 否 |
| publisher | 发布人 | string | 否 |
| publishTime | 发布时间（`yyyy-MM-dd HH:mm:ss`） | string | 否 |
     */

    private String title;
    private String content;
    private String publisher;
    private LocalDateTime publishTime;
}
