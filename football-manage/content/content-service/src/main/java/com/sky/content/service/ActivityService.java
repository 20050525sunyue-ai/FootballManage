package com.sky.content.service;

import com.sky.content.model.dto.AddActivityDto;
import com.sky.content.model.dto.ActivityPageDto;
import com.sky.content.model.dto.UpdateActivityDto;
import com.sky.content.model.po.Activity;
import com.sky.base.model.PageResult;

public interface ActivityService {

    // 分页查询活动信息
    PageResult<Activity> ActivityPageQuery(ActivityPageDto activityPageDto);

    // 根据主键查询活动详情
    Activity getActivityById(Long id);

    // 新增活动
    Activity addActivity(AddActivityDto addActivityDto);

    // 修改活动
    Activity updateActivity(Long id, UpdateActivityDto updateActivityDto);

    // 删除活动
    boolean deleteActivity(Long id);
}
