package com.sky.content.service;

import com.sky.content.model.dto.AddTrainingDto;
import com.sky.content.model.dto.TrainingPageDto;
import com.sky.content.model.dto.UpdateTrainingDto;
import com.sky.content.model.po.Training;
import com.sky.base.model.PageResult;

public interface TrainingService {

    // 分页查询训练信息
    PageResult<Training> TrainingPageQuery(TrainingPageDto trainingPageDto);

    // 根据主键查询训练详情
    Training getTrainingById(Long id);

    // 新增训练
    Training addTraining(AddTrainingDto addTrainingDto);

    // 修改训练
    Training updateTraining(Long id, UpdateTrainingDto updateTrainingDto);

    // 删除训练
    boolean deleteTraining(Long id);
}
