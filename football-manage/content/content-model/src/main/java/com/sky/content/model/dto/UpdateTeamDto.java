package com.sky.content.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class UpdateTeamDto {

    /*
    | 英文字段 | 中文含义 | 类型 | 必填 |
|---------|---------|------|------|
| id | 要修改的队伍主键 | number | 是 |
| teamName | 队伍名称 | string | 按你后端规则 |
| captain | 队长姓名 | string | 按你后端规则 |
| description | 队伍简介 | string | 按你后端规则 |
     */


    private Long id;

    private String teamName;

    private String captain;

    private String description;
}
