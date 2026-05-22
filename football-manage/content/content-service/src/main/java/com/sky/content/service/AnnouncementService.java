package com.sky.content.service;

import com.sky.content.model.dto.AddAnnouncementDto;
import com.sky.content.model.dto.AnnouncementPageDto;
import com.sky.content.model.dto.UpdateAnnouncementDto;
import com.sky.content.model.po.Announcement;
import com.sky.base.model.PageResult;

public interface AnnouncementService {

    // 分页查询公告信息
    PageResult<Announcement> AnnouncementPageQuery(AnnouncementPageDto announcementPageDto);

    // 根据主键查询公告详情
    Announcement getAnnouncementById(Long id);

    // 新增公告
    Announcement addAnnouncement(AddAnnouncementDto addAnnouncementDto);

    // 修改公告
    Announcement updateAnnouncement(Long id, UpdateAnnouncementDto updateAnnouncementDto);

    // 删除公告
    boolean deleteAnnouncement(Long id);
}
