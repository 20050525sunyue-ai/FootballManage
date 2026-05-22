package com.sky.content.mapper;

import com.github.pagehelper.Page;
import com.sky.content.model.dto.MatchPageDto;
import com.sky.content.model.dto.TeamPageDto;
import com.sky.content.model.po.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 队伍表 {@code team}
 */
@Mapper
public interface TeamMapper {


    /**
     * 新增；主键回填至 {@link Team#getId()}
     */
    int insert(Team team);

    int deleteById(Long id);

    int update(Team team);

    Team getById(Long id);

    /**
     * 条件查询；{@code q} 为 {@code null} 则查全部。
     * 支持按 {@link Team#getTeamName()} 模糊、{@link Team#getCaptain()} 模糊。
     */
    List<Team> list(@Param("q") Team q);

    // 分页查询
    Page<Team> pageQuery(TeamPageDto teamPageDto);


}
