package com.sky.content.mapper;

import com.sky.content.model.dto.TrainingPageDto;
import com.sky.content.model.po.Training;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TrainingMapper {

    // 分页查询（配合 PageHelper）
    List<Training> TrainingPageQuery(TrainingPageDto trainingPageDto);

    // 新增；主键回填至 Training.id
    int insert(Training row);

    // 更新训练
    int update(Training row);

    // 删除训练
    int deleteById(Long id);

    // 根据主键查询
    Training getById(Long id);
}
