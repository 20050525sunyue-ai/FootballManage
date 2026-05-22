package com.sky.content.model.dto;


import lombok.Data;

@Data
public class TeamPageDto {

    /**
     * 关键词：队伍名、队长模糊搜（可选）
     */
    private String keyword;

    /** 当前页码，默认由 Service 层兜底为 1 */
    private Integer pageNum;

    /** 每页条数，默认由 Service 层兜底为 10 */
    private Integer pageSize;
}
