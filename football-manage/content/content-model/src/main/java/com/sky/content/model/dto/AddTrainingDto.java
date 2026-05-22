package com.sky.content.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddTrainingDto {

    /*
    ### 请求（Body JSON）

| 英文字段 | 中文含义 | 类型 | 必填 |
|---------|---------|------|------|
| team | 训练队伍名称 | string | 是 |
| trainingTime | 训练时间（`yyyy-MM-dd HH:mm:ss`） | string | 是 |
| location | 训练地点 | string | 是 |
| content | 训练内容 | string | 是 |
| coach | 负责人/教练 | string | 是 |
     */

    private String team;
    private LocalDateTime trainingTime;
    private String location;
    private String content;
    private String coach;
}
