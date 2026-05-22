package com.sky.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.content.model.dto.AddActivityDto;
import com.sky.content.model.dto.ActivityPageDto;
import com.sky.content.model.dto.UpdateActivityDto;
import com.sky.content.model.po.Activity;
import com.sky.content.mapper.ActivityMapper;
import com.sky.base.model.PageResult;
import com.sky.content.service.ActivityService;
import com.sky.base.utils.ToPageResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public PageResult<Activity> ActivityPageQuery(ActivityPageDto activityPageDto) {
        int pageNum = activityPageDto.getPageNum() == null ? 1 : activityPageDto.getPageNum();
        int pageSize = activityPageDto.getPageSize() == null ? 10 : activityPageDto.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Activity> list = activityMapper.ActivityPageQuery(activityPageDto);
        PageInfo<Activity> pageInfo = new PageInfo<>(list);
        return ToPageResultUtil.from(pageInfo);
    }

    @Override
    public Activity getActivityById(Long id) {
        Activity a = activityMapper.getById(id);
        if (a == null) {
            throw new RuntimeException("活动不存在");
        }
        return a;
    }

    @Override
    public Activity addActivity(AddActivityDto addActivityDto) {
        if (addActivityDto.getTitle() == null || addActivityDto.getActivityTime() == null
                || addActivityDto.getLocation() == null || addActivityDto.getContent() == null
                || addActivityDto.getLeader() == null) {
            throw new RuntimeException("必填项不能为空");
        }
        Activity row = new Activity();
        BeanUtils.copyProperties(addActivityDto, row);
        activityMapper.insert(row);
        // 获取插入的id
        return activityMapper.getById(row.getId());
    }

    // 更新活动（仅更新 DTO 中非 null 字段）
    @Override
    public Activity updateActivity(Long id, UpdateActivityDto dto) {
        Activity a = activityMapper.getById(id);
        if (a == null) {
            throw new RuntimeException("活动不存在");
        }
        if (dto.getTitle() != null) {
            a.setTitle(dto.getTitle());
        }
        if (dto.getActivityTime() != null) {
            a.setActivityTime(dto.getActivityTime());
        }
        if (dto.getLocation() != null) {
            a.setLocation(dto.getLocation());
        }
        if (dto.getContent() != null) {
            a.setContent(dto.getContent());
        }
        if (dto.getLeader() != null) {
            a.setLeader(dto.getLeader());
        }
        activityMapper.update(a);
        return activityMapper.getById(id);
    }

    // 删除活动
    @Override
    public boolean deleteActivity(Long id) {
        int i = activityMapper.deleteById(id);
        if (i == 0) {
            throw new RuntimeException("删除失败");
        }
        return true;
    }
}
