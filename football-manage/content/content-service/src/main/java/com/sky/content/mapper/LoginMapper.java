package com.sky.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.sky.content.model.dto.RegisterDto;
import com.sky.content.model.dto.TeamPageDto;
import com.sky.content.model.po.SysUser;
import com.sky.content.model.po.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface LoginMapper extends BaseMapper<SysUser> {

        // 根据用户名查询用户信息
    SysUser selectUserInfo(String username);


    // 注册
    int register(RegisterDto registerDto);
}
