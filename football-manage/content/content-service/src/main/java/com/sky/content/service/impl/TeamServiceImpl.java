package com.sky.content.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.content.model.dto.AddTeamDto;
import com.sky.content.model.dto.TeamPageDto;
import com.sky.content.model.dto.UpdateTeamDto;
import com.sky.content.model.po.Team;
import com.sky.content.mapper.TeamMapper;
import com.sky.base.model.PageResult;
import com.sky.content.service.TeamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamMapper TeamMapper;
    @Override
    public PageResult<Team> TeamPageQuery(TeamPageDto teamPageDto) {
        PageHelper.startPage(teamPageDto.getPageNum(), teamPageDto.getPageSize());
        Page<Team> page = TeamMapper.pageQuery(teamPageDto);
        return new PageResult<>(page.getResult(),page.getTotal(),teamPageDto.getPageNum(),teamPageDto.getPageSize());
    }

    @Override
    public Team addTeam(AddTeamDto addTeamDto) {
        Team team = new Team();
        BeanUtils.copyProperties(addTeamDto,team);
        TeamMapper.insert(team);
        // 获取插入的id
        return TeamMapper.getById(team.getId());
    }

    // 更新队伍
    @Override
    public Team updateTeam(UpdateTeamDto updateTeamDto) {
        Team team = new Team();
        BeanUtils.copyProperties(updateTeamDto,team);
        TeamMapper.update(team);
        return TeamMapper.getById(team.getId());
    }

    // 删除队伍
    @Override
    public void deleteTeam(Long id) {
        int i = TeamMapper.deleteById(id);
        if (i == 0) {
            throw new RuntimeException("删除失败");
        }
    }
}
