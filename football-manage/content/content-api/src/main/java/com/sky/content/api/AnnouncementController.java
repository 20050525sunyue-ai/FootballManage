package com.sky.content.api;

import com.sky.content.model.dto.AddAnnouncementDto;
import com.sky.content.model.dto.AnnouncementPageDto;
import com.sky.content.model.dto.UpdateAnnouncementDto;
import com.sky.content.model.po.Announcement;
import com.sky.base.model.PageResult;
import com.sky.base.model.Result;
import com.sky.content.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;



    // 公告分页查询
    @GetMapping("/announcement/page")
    public Result<PageResult<Announcement>> announcementPage(AnnouncementPageDto announcementPageDto) {
        return Result.success(announcementService.AnnouncementPageQuery(announcementPageDto));
    }

    // 公告详情（Path: id）
    @GetMapping("/announcement/{id}")
    public Result<Announcement> getAnnouncement(@PathVariable Long id) {
        return Result.success(announcementService.getAnnouncementById(id));
    }

    // 新增公告
    @PostMapping("/announcement")
    public Result<Announcement> addAnnouncement(@RequestBody AddAnnouncementDto addAnnouncementDto) {
        return Result.success(announcementService.addAnnouncement(addAnnouncementDto));
    }

    // 修改公告（Path: id + Body 部分字段）
    @PutMapping("/announcement/{id}")
    public Result<Announcement> updateAnnouncement(@PathVariable Long id, @RequestBody UpdateAnnouncementDto updateAnnouncementDto) {
        return Result.success(announcementService.updateAnnouncement(id, updateAnnouncementDto));
    }

    // 删除公告
    @DeleteMapping("/announcement/{id}")
    public Result<Boolean> deleteAnnouncement(@PathVariable Long id) {
        return Result.success(announcementService.deleteAnnouncement(id));
    }
}
