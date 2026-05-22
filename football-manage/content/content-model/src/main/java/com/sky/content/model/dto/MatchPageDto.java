package com.sky.content.model.dto;


import lombok.Data;

@Data
public class MatchPageDto {

    /*
    | keyword | 关键词（名称、对手、场地模糊搜） | string | 否 | — |
    | status | 比赛状态筛选 | number | 否 | 不传表示不限 |    0：未开始，1：已结束
    | pageNum | 当前页码 | number | 否 | 1 |
    | pageSize | 每页条数 | number | 否 | 10 |
     */

    private String keyword;
    private Integer status;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
