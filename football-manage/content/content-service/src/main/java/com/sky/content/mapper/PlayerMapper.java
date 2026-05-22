package com.sky.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.sky.content.model.dto.PlayerPageDto;
import com.sky.content.model.po.Player;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlayerMapper  extends BaseMapper<Player> {

    // 分页查询球员
    Page<Player> PlayerPageQuery(PlayerPageDto playerPageDto);
}
