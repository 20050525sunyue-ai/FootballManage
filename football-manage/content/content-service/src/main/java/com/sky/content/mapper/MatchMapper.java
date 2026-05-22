package com.sky.content.mapper;

import com.sky.content.model.dto.MatchPageDto;
import com.sky.content.model.po.MatchInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MatchMapper {

    // 分页查询（配合 PageHelper）
    List<MatchInfo> MatchPageQuery(MatchPageDto matchPageDto);

    // 新增；主键回填至 MatchInfo.id
    int insert(MatchInfo row);

    // 更新比赛
    int update(MatchInfo row);

    // 删除比赛
    int deleteById(Long id);

    // 根据主键查询
    MatchInfo getById(Long id);
}
