package com.bossien.service;

import com.bossien.entity.ExerciseAnswers;

import java.util.List;

/**
 *
 * ap_company 表数据服务层接口
 *
 */
public interface IExerciseAnswersService{
    List<String> getIds(List<ExerciseAnswers> list);

    /**
     * 查询练习答题列表
     * @param project_id
     * @param user_id
     * @return
     */
    List<ExerciseAnswers> selectList(String project_id, String user_id);

}