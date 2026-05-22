package com.sky.content.model.dto;

import lombok.Data;

@Data
public class ActivityPageDto {

    /*
    | keyword | 关键词（标题、地点、负责人模糊搜） | string | 否 | — |
    | pageNum | 当前页码 | number | 否 | 1 |
    | pageSize | 每页条数 | number | 否 | 10 |
     */

    private String keyword;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
