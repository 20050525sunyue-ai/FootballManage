package com.sky.content.api;

import com.sky.content.model.dto.AddActivityDto;
import com.sky.content.model.dto.ActivityPageDto;
import com.sky.content.model.dto.UpdateActivityDto;
import com.sky.content.model.po.Activity;
import com.sky.base.model.PageResult;
import com.sky.base.model.Result;
import com.sky.content.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ActivityController {

    @Autowired
    private ActivityService activityService;



    // 活动分页查询
    @GetMapping("/activity/page")
    public Result<PageResult<Activity>> activityPage(ActivityPageDto activityPageDto) {
        return Result.success(activityService.ActivityPageQuery(activityPageDto));
    }

    // 活动详情（Path: id）
    @GetMapping("/activity/{id}")
    public Result<Activity> getActivity(@PathVariable Long id) {
        return Result.success(activityService.getActivityById(id));
    }

    // 新增活动
    @PostMapping("/activity")
    public Result<Activity> addActivity(@RequestBody AddActivityDto addActivityDto) {
        return Result.success(activityService.addActivity(addActivityDto));
    }

    // 修改活动（Path: id + Body 部分字段）
    @PutMapping("/activity/{id}")
    public Result<Activity> updateActivity(@PathVariable Long id, @RequestBody UpdateActivityDto updateActivityDto) {
        return Result.success(activityService.updateActivity(id, updateActivityDto));
    }

    // 删除活动
    @DeleteMapping("/activity/{id}")
    public Result<Boolean> deleteActivity(@PathVariable Long id) {
        return Result.success(activityService.deleteActivity(id));
    }
}
