package com.sky.content.model.dto;


import lombok.Data;

@Data
public class AddTeamDto {

    /*
    ### 请求（Body JSON）

| 英文字段 | 中文含义 | 类型 | 必填 |
|---------|---------|------|------|
| teamName | 队伍名称 | string | 是 |
| captain | 队长姓名 | string | 是 |
| description | 队伍简介 | string | 否 |
     */


    private String teamName;
    private String captain;
    private String description;
}
