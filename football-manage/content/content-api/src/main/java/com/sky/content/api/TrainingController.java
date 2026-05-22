package com.sky.content.api;

import com.sky.content.model.dto.AddTrainingDto;
import com.sky.content.model.dto.TrainingPageDto;
import com.sky.content.model.dto.UpdateTrainingDto;
import com.sky.content.model.po.Training;
import com.sky.base.model.PageResult;
import com.sky.base.model.Result;
import com.sky.content.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TrainingController {

    @Autowired
    private TrainingService trainingService;



    // 训练分页查询
    @GetMapping("/training/page")
    public Result<PageResult<Training>> trainingPage(TrainingPageDto trainingPageDto) {
        return Result.success(trainingService.TrainingPageQuery(trainingPageDto));
    }

    // 训练详情（Path: id）
    @GetMapping("/training/{id}")
    public Result<Training> getTraining(@PathVariable Long id) {
        return Result.success(trainingService.getTrainingById(id));
    }

    // 新增训练
    @PostMapping("/training")
    public Result<Training> addTraining(@RequestBody AddTrainingDto addTrainingDto) {
        return Result.success(trainingService.addTraining(addTrainingDto));
    }

    // 修改训练（Path: id + Body 部分字段）
    @PutMapping("/training/{id}")
    public Result<Training> updateTraining(@PathVariable Long id, @RequestBody UpdateTrainingDto updateTrainingDto) {
        return Result.success(trainingService.updateTraining(id, updateTrainingDto));
    }

    // 删除训练
    @DeleteMapping("/training/{id}")
    public Result<Boolean> deleteTraining(@PathVariable Long id) {
        return Result.success(trainingService.deleteTraining(id));
    }
}
