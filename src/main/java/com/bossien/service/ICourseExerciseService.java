package com.bossien.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.CourseInfo;
import com.bossien.entity.CourseQuestion;
import com.bossien.entity.request.ExerciseCourseQuestion;
import com.bossien.entity.request.PerfectExercise;
import com.bossien.entity.request.QuestionTypeList;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/18.
 */
public interface ICourseExerciseService extends IService<CourseQuestion> {

    Map<String, Object> selectCourseQuestion(Page<Map<Object,Object>> page, ExerciseCourseQuestion cq);

    Map<String, Object> selectAllQuestion(Page<Map<Object,Object>> page, Map<String,Object> map);

    Map<String, Object> selectNoExerciseQuestion(Page<Map<Object,Object>> page, Map<String,Object> map);

    Map<String, Object> getQuestionMap(Page<Map<Object, Object>> page, Map<String, Object> map, List<String> questionIds);

    List<String> getQuestionIds(Map<String, Object> map);

    /**
     * 查询专项练习各个类型题量
     * @param per
     * @return
     */
    List<Map<String, Object>> selectExerciseMap(PerfectExercise per);

    /**
     * 查询易错题数量
     * @param projectId
     * @param userId
     * @return
     */
    int queryEasyQuestion(String projectId,String userId);

    public Map<String, Object> selectQuestionTypeExerciseMap(Page<Map<Object,Object>> page, QuestionTypeList qtl);
}
