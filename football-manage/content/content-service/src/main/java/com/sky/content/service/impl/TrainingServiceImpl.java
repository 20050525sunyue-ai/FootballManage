package com.sky.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.content.model.dto.AddTrainingDto;
import com.sky.content.model.dto.TrainingPageDto;
import com.sky.content.model.dto.UpdateTrainingDto;
import com.sky.content.model.po.Training;
import com.sky.content.mapper.TrainingMapper;
import com.sky.base.model.PageResult;
import com.sky.content.service.TrainingService;
import com.sky.base.utils.ToPageResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingMapper trainingMapper;

    @Override
    public PageResult<Training> TrainingPageQuery(TrainingPageDto trainingPageDto) {
        int pageNum = trainingPageDto.getPageNum() == null ? 1 : trainingPageDto.getPageNum();
        int pageSize = trainingPageDto.getPageSize() == null ? 10 : trainingPageDto.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Training> list = trainingMapper.TrainingPageQuery(trainingPageDto);
        PageInfo<Training> pageInfo = new PageInfo<>(list);
        return ToPageResultUtil.from(pageInfo);
    }

    @Override
    public Training getTrainingById(Long id) {
        Training t = trainingMapper.getById(id);
        if (t == null) {
            throw new RuntimeException("训练记录不存在");
        }
        return t;
    }

    @Override
    public Training addTraining(AddTrainingDto addTrainingDto) {
        if (addTrainingDto.getTeam() == null || addTrainingDto.getTrainingTime() == null
                || addTrainingDto.getLocation() == null || addTrainingDto.getContent() == null
                || addTrainingDto.getCoach() == null) {
            throw new RuntimeException("必填项不能为空");
        }
        Training row = new Training();
        BeanUtils.copyProperties(addTrainingDto, row);
        trainingMapper.insert(row);
        // 获取插入的id
        return trainingMapper.getById(row.getId());
    }

    // 更新训练（仅更新 DTO 中非 null 字段）
    @Override
    public Training updateTraining(Long id, UpdateTrainingDto dto) {
        Training t = trainingMapper.getById(id);
        if (t == null) {
            throw new RuntimeException("训练记录不存在");
        }
        if (dto.getTeam() != null) {
            t.setTeam(dto.getTeam());
        }
        if (dto.getTrainingTime() != null) {
            t.setTrainingTime(dto.getTrainingTime());
        }
        if (dto.getLocation() != null) {
            t.setLocation(dto.getLocation());
        }
        if (dto.getContent() != null) {
            t.setContent(dto.getContent());
        }
        if (dto.getCoach() != null) {
            t.setCoach(dto.getCoach());
        }
        trainingMapper.update(t);
        return trainingMapper.getById(id);
    }

    // 删除训练
    @Override
    public boolean deleteTraining(Long id) {
        int i = trainingMapper.deleteById(id);
        if (i == 0) {
            throw new RuntimeException("删除失败");
        }
        return true;
    }
}
