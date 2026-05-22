package com.sky.content.service;

import com.sky.content.model.dto.AddMatchDto;
import com.sky.content.model.dto.MatchPageDto;
import com.sky.content.model.dto.UpdateMatchDto;
import com.sky.content.model.po.MatchInfo;
import com.sky.base.model.PageResult;

public interface MatchService {

    // 分页查询比赛信息
    PageResult<MatchInfo> MatchPageQuery(MatchPageDto matchPageDto);

    // 根据主键查询比赛详情
    MatchInfo getMatchById(Long id);

    // 新增比赛
    MatchInfo addMatch(AddMatchDto addMatchDto);

    // 修改比赛
    MatchInfo updateMatch(Long id, UpdateMatchDto updateMatchDto);

    // 删除比赛
    boolean deleteMatch(Long id);
}
