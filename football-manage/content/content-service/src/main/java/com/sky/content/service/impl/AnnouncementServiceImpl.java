package com.sky.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.content.model.dto.AddAnnouncementDto;
import com.sky.content.model.dto.AnnouncementPageDto;
import com.sky.content.model.dto.UpdateAnnouncementDto;
import com.sky.content.model.po.Announcement;
import com.sky.content.mapper.AnnouncementMapper;
import com.sky.base.model.PageResult;
import com.sky.content.service.AnnouncementService;
import com.sky.base.utils.ToPageResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public PageResult<Announcement> AnnouncementPageQuery(AnnouncementPageDto announcementPageDto) {
        int pageNum = announcementPageDto.getPageNum() == null ? 1 : announcementPageDto.getPageNum();
        int pageSize = announcementPageDto.getPageSize() == null ? 10 : announcementPageDto.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Announcement> list = announcementMapper.AnnouncementPageQuery(announcementPageDto);
        PageInfo<Announcement> pageInfo = new PageInfo<>(list);
        return ToPageResultUtil.from(pageInfo);
    }

    @Override
    public Announcement getAnnouncementById(Long id) {
        Announcement a = announcementMapper.getById(id);
        if (a == null) {
            throw new RuntimeException("公告不存在");
        }
        return a;
    }

    @Override
    public Announcement addAnnouncement(AddAnnouncementDto addAnnouncementDto) {
        if (addAnnouncementDto.getTitle() == null || addAnnouncementDto.getContent() == null
                || addAnnouncementDto.getPublisher() == null || addAnnouncementDto.getPublishTime() == null) {
            throw new RuntimeException("必填项不能为空");
        }
        Announcement row = new Announcement();
        BeanUtils.copyProperties(addAnnouncementDto, row);
        announcementMapper.insert(row);
        // 获取插入的id
        return announcementMapper.getById(row.getId());
    }

    // 更新公告（仅更新 DTO 中非 null 字段）
    @Override
    public Announcement updateAnnouncement(Long id, UpdateAnnouncementDto dto) {
        Announcement a = announcementMapper.getById(id);
        if (a == null) {
            throw new RuntimeException("公告不存在");
        }
        if (dto.getTitle() != null) {
            a.setTitle(dto.getTitle());
        }
        if (dto.getContent() != null) {
            a.setContent(dto.getContent());
        }
        if (dto.getPublisher() != null) {
            a.setPublisher(dto.getPublisher());
        }
        if (dto.getPublishTime() != null) {
            a.setPublishTime(dto.getPublishTime());
        }
        announcementMapper.update(a);
        return announcementMapper.getById(id);
    }

    // 删除公告
    @Override
    public boolean deleteAnnouncement(Long id) {
        int i = announcementMapper.deleteById(id);
        if (i == 0) {
            throw new RuntimeException("删除失败");
        }
        return true;
    }
}
