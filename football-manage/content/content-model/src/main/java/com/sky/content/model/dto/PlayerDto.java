package com.sky.content.model.dto;


import lombok.Data;

@Data
public class PlayerDto {

    /*| 英文字段 | 中文含义 | 类型 | 必填 |
|---------|---------|------|------|
| name | 姓名 | string | 是 |
| studentId | 学号 | string | 是，全局唯一 |
| age | 年龄 | number | 是 |
| phone | 手机号 | string | 是 |
| height | 身高（厘米） | number | 是 |
| weight | 体重（千克） | number | 是 |
| avatar | 头像 | string | 否 |
| description | 个人简介 | string | 否 |
| teamId | 所属队伍 ID | number | 否 |
     */

    private String name;
    private String studentId;
    private Integer age;
    private String phone;
    private Integer height;
    private Integer weight;
    private String avatar;
    private String description;
    private Long teamId;


}
