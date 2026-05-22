package com.sky.content.mapper;

import com.sky.content.model.dto.ActivityPageDto;
import com.sky.content.model.po.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityMapper {

    // 分页查询（配合 PageHelper）
    List<Activity> ActivityPageQuery(ActivityPageDto activityPageDto);

    // 新增；主键回填至 Activity.id
    int insert(Activity row);

    // 更新活动
    int update(Activity row);

    // 删除活动
    int deleteById(Long id);

    // 根据主键查询
    Activity getById(Long id);
}
