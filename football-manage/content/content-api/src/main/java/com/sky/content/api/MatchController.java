package com.sky.content.api;

import com.sky.content.model.dto.AddMatchDto;
import com.sky.content.model.dto.MatchPageDto;
import com.sky.content.model.dto.UpdateMatchDto;
import com.sky.content.model.po.MatchInfo;
import com.sky.base.model.PageResult;
import com.sky.base.model.Result;
import com.sky.content.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;



    // 比赛分页查询
    @GetMapping("/match/page")
    public Result<PageResult<MatchInfo>> matchPage(MatchPageDto matchPageDto) {
        return Result.success(matchService.MatchPageQuery(matchPageDto));
    }

    // 比赛详情（Path: id）
    @GetMapping("/match/{id}")
    public Result<MatchInfo> getMatch(@PathVariable Long id) {
        return Result.success(matchService.getMatchById(id));
    }

    // 新增比赛
    @PostMapping("/match")
    public Result<MatchInfo> addMatch(@RequestBody AddMatchDto addMatchDto) {
        return Result.success(matchService.addMatch(addMatchDto));
    }

    // 修改比赛（Path: id + Body 部分字段）
    @PutMapping("/match/{id}")
    public Result<MatchInfo> updateMatch(@PathVariable Long id, @RequestBody UpdateMatchDto updateMatchDto) {
        return Result.success(matchService.updateMatch(id, updateMatchDto));
    }

    // 删除比赛
    @DeleteMapping("/match/{id}")
    public Result<Boolean> deleteMatch(@PathVariable Long id) {
        return Result.success(matchService.deleteMatch(id));
    }
}
