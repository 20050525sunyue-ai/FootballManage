package com.sky.content.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.content.model.dto.PlayerDto;
import com.sky.content.model.dto.PlayerPageDto;
import com.sky.content.model.po.Player;
import com.sky.content.mapper.PlayerMapper;
import com.sky.base.model.PageResult;
import com.sky.content.service.PlayerService;

import com.sky.base.utils.AliOssUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerMapper PlayerMapper;

    @Autowired
    private AliOssUtil aliOssUtil;

    @Override
    public PageResult PlayerPageQuery(PlayerPageDto playerPageDto) {
        PageHelper.startPage(playerPageDto.getPageNum(), playerPageDto.getPageSize());
        Page<Player> pageResult = PlayerMapper.PlayerPageQuery(playerPageDto);
        return new PageResult(pageResult.getResult(),pageResult.getTotal(),playerPageDto.getPageNum(),playerPageDto.getPageSize());
    }

    // 添加球员
    @Override
    public Player addPlayer(PlayerDto playerDto) {
        // 把头像上传到阿里云oss
        //① 用户选择图片 → ② 前端调用 `POST /api/upload` → ③ 后端写入阿里云 OSS → ④ 返回图片 URL → ⑤ 前端把 URL 放入 `avatar` → ⑥ 调用新增球员接口 → ⑦ 后端将 URL 写入数据库。
        LocalDateTime now = LocalDateTime.now();
        Player player = new Player();
        BeanUtils.copyProperties(playerDto, player);
        player.setCreateTime( now);
        player.setUpdateTime(now);

        PlayerMapper.insert(player);
        return PlayerMapper.selectById(player.getId());
    }

    // 修改球员
    @Override
    public Player updatePlayer(Long id, PlayerDto playerDto) {
        Player player = new Player();
        BeanUtils.copyProperties(playerDto, player);
        // 路径上的主键必须写入实体，否则 MP 的 updateById 会得到 WHERE id = null，表现为 Updates: 0
        player.setId(id);
        player.setUpdateTime(LocalDateTime.now());
        PlayerMapper.updateById(player);
        return PlayerMapper.selectById(id);
    }

    @Override
    public Player getPlayerById(Long id) {
        return PlayerMapper.selectById(id);
    }

    @Override
    public Boolean deletePlayer(Long id) {
        return PlayerMapper.deleteById(id)>0;
    }


}
