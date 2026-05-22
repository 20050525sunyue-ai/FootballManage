package com.sky.content.mapper;

import com.sky.content.model.dto.AnnouncementPageDto;
import com.sky.content.model.po.Announcement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnnouncementMapper {

    // 分页查询（配合 PageHelper）
    List<Announcement> AnnouncementPageQuery(AnnouncementPageDto announcementPageDto);

    // 新增；主键回填至 Announcement.id
    int insert(Announcement row);

    // 更新公告
    int update(Announcement row);

    // 删除公告
    int deleteById(Long id);

    // 根据主键查询
    Announcement getById(Long id);
}
