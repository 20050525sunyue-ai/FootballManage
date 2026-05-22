package com.sky.content.api;


import com.sky.content.model.dto.TeamPageDto;
import com.sky.content.model.dto.AddTeamDto;
import com.sky.content.model.dto.UpdateTeamDto;
import com.sky.content.model.po.Team;
import com.sky.base.model.PageResult;
import com.sky.base.model.Result;
import com.sky.content.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;



    // 队伍分页查询
    @GetMapping("/team/page")
    public Result<PageResult> getUserInfo (TeamPageDto teamPageDto){
        PageResult pageResult = teamService.TeamPageQuery(teamPageDto);
        return Result.success(pageResult);
    }


    // 新增队伍
    @PostMapping("/team")
    public Result<Team> addTeam (@RequestBody AddTeamDto addTeamDto){
        return Result.success(teamService.addTeam(addTeamDto));
    }

    // 修改队伍
    @PutMapping("/team")
    public Result<Team> updateTeam(@RequestBody UpdateTeamDto updateTeamDto) {
        return Result.success(teamService.updateTeam(updateTeamDto));
    }

    // 删除队伍
    @DeleteMapping("/team/{id}")
    public Result deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return Result.success();
    }


}
