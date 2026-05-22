package com.sky.content.service;

import com.sky.content.model.dto.AddTeamDto;
import com.sky.content.model.dto.TeamPageDto;
import com.sky.content.model.dto.UpdateTeamDto;
import com.sky.content.model.po.Team;
import com.sky.base.model.PageResult;

public interface TeamService {

    // 分页查询队伍信息
    PageResult TeamPageQuery(TeamPageDto teamPageDto);

    // 新增队伍
    Team addTeam(AddTeamDto addTeamDto);

    // 修改队伍
    Team updateTeam(UpdateTeamDto updateTeamDto);

    // 删除队伍
    void deleteTeam(Long id);
}

