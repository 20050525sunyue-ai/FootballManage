package com.sky.content.model.dto;


import lombok.Data;

@Data
public class PlayerPageDto {

    /*
    | 英文字段 | 中文含义 | 类型 | 必填 | 默认 |
|---------|---------|------|------|------|
| keyword | 关键词（姓名、学号、手机模糊搜） | string | 否 | — |
| teamId | 所属队伍主键，仅看该队球员 | number | 否 | — |
| pageNum | 当前页码 | number | 否 | 1 |
| pageSize | 每页条数 | number | 否 | 10 |
     */
    private String keyword;
    private Long teamId;
    private Integer pageNum;
    private Integer pageSize;
}
