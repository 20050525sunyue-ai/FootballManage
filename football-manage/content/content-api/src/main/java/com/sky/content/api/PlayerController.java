package com.sky.content.api;


import com.sky.content.model.dto.PlayerDto;
import com.sky.content.model.dto.PlayerPageDto;
import com.sky.content.model.po.Player;
import com.sky.base.model.PageResult;
import com.sky.base.model.Result;
import com.sky.content.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerController {

    @Autowired
    PlayerService PlayerService;


    // 球员分页
    @GetMapping("/player/page")
    public Result<PageResult> PlayerPage(PlayerPageDto playerPageDto){
        PageResult pageResult = PlayerService.PlayerPageQuery(playerPageDto);
        return Result.success(pageResult);
    }


    // 新增球员
    @PostMapping("/player")
    public Result<Player> addPlayer(@RequestBody PlayerDto player) {
        return Result.success(PlayerService.addPlayer(player));
    }

    // 球员详情
    @GetMapping("/player/{id}")
    public Result<Player> getPlayer(@PathVariable Long id) {
        return Result.success(PlayerService.getPlayerById(id));
    }


    // 修改球员
    @PutMapping("/player/{id}")
    public Result<Player> updatePlayer(@PathVariable Long id, @RequestBody PlayerDto player) {
        return Result.success(PlayerService.updatePlayer(id, player));
    }

    // 删除球员
    @DeleteMapping("/player/{id}")
    public Result<Boolean> deletePlayer(@PathVariable Long id) {
        return Result.success(PlayerService.deletePlayer(id));
    }


}
