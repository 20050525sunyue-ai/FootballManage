package com.sky.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.content.model.dto.AddMatchDto;
import com.sky.content.model.dto.MatchPageDto;
import com.sky.content.model.dto.UpdateMatchDto;
import com.sky.content.model.po.MatchInfo;
import com.sky.content.mapper.MatchMapper;
import com.sky.base.model.PageResult;
import com.sky.content.service.MatchService;
import com.sky.base.utils.ToPageResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchMapper matchMapper;

    @Override
    public PageResult<MatchInfo> MatchPageQuery(MatchPageDto matchPageDto) {
        int pageNum = matchPageDto.getPageNum() == null ? 1 : matchPageDto.getPageNum();
        int pageSize = matchPageDto.getPageSize() == null ? 10 : matchPageDto.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<MatchInfo> list = matchMapper.MatchPageQuery(matchPageDto);
        PageInfo<MatchInfo> pageInfo = new PageInfo<>(list);
        return ToPageResultUtil.from(pageInfo);
    }

    @Override
    public MatchInfo getMatchById(Long id) {
        MatchInfo m = matchMapper.getById(id);
        if (m == null) {
            throw new RuntimeException("比赛不存在");
        }
        return m;
    }

    @Override
    public MatchInfo addMatch(AddMatchDto addMatchDto) {
        if (addMatchDto.getTitle() == null || addMatchDto.getOpponent() == null
                || addMatchDto.getMatchTime() == null || addMatchDto.getLocation() == null
                || addMatchDto.getStatus() == null) {
            throw new RuntimeException("必填项不能为空");
        }
        MatchInfo row = new MatchInfo();
        BeanUtils.copyProperties(addMatchDto, row);
        matchMapper.insert(row);
        // 获取插入的id
        return matchMapper.getById(row.getId());
    }

    // 更新比赛（仅更新 DTO 中非 null 字段）
    @Override
    public MatchInfo updateMatch(Long id, UpdateMatchDto dto) {
        MatchInfo m = matchMapper.getById(id);
        if (m == null) {
            throw new RuntimeException("比赛不存在");
        }
        if (dto.getTitle() != null) {
            m.setTitle(dto.getTitle());
        }
        if (dto.getOpponent() != null) {
            m.setOpponent(dto.getOpponent());
        }
        if (dto.getMatchTime() != null) {
            m.setMatchTime(dto.getMatchTime());
        }
        if (dto.getLocation() != null) {
            m.setLocation(dto.getLocation());
        }
        if (dto.getScore() != null) {
            m.setScore(dto.getScore());
        }
        if (dto.getResult() != null) {
            m.setResult(dto.getResult());
        }
        if (dto.getDescription() != null) {
            m.setDescription(dto.getDescription());
        }
        if (dto.getStatus() != null) {
            m.setStatus(dto.getStatus());
        }
        matchMapper.update(m);
        return matchMapper.getById(id);
    }

    // 删除比赛
    @Override
    public boolean deleteMatch(Long id) {
        int i = matchMapper.deleteById(id);
        if (i == 0) {
            throw new RuntimeException("删除失败");
        }
        return true;
    }
}
