package com.sky.content.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateTrainingDto {

    /*
    ### 请求（Body JSON，可部分更新；为 null 的字段不修改）

| 英文字段 | 中文含义 | 类型 | 必填 |
|---------|---------|------|------|
| team | 训练队伍名称 | string | 否 |
| trainingTime | 训练时间（`yyyy-MM-dd HH:mm:ss`） | string | 否 |
| location | 训练地点 | string | 否 |
| content | 训练内容 | string | 否 |
| coach | 负责人/教练 | string | 否 |
     */

    private String team;
    private LocalDateTime trainingTime;
    private String location;
    private String content;
    private String coach;
}
