package com.sky.content.service;


import com.sky.content.model.dto.PlayerDto;
import com.sky.content.model.dto.PlayerPageDto;
import com.sky.content.model.po.Player;
import com.sky.base.model.PageResult;

public interface PlayerService {


    // 球员分页查询
    PageResult PlayerPageQuery(PlayerPageDto playerPageDto);


    // 新增球员
    Player addPlayer(PlayerDto player);

    // 修改球员
    Player updatePlayer(Long id, PlayerDto player);

    // 根据id 查询球员
    Player getPlayerById(Long id);

    // 删除球员
    Boolean deletePlayer(Long id);
}
