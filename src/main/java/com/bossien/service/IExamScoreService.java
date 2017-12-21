package com.bossien.service;


import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.ExamPaperInfo;
import com.bossien.entity.ExamScore;

import java.util.List;
import java.util.Map;

import java.util.List;

/**
 *
 * exam_score 表数据服务层接口
 *
 */
public interface IExamScoreService  extends IService<ExamScore> {
    /**
     * 查询单条记录
     * @param examScore
     * @return
     */
    ExamScore selectOne(ExamScore examScore);

    /**
     * 查询考试学员数量
     * @param map
     * @return
     */
    Integer userScoreCount(Map map);

    List<Map<String,Object>> userScoreList(Map map);

    /**
     * 查询最好考试成绩
     * @param examScore
     * @return
     */
    Integer selectExamScore(ExamScore examScore);

    /**
     * 查询考试记录
     * @param examScore
     * @return
     */
    List<ExamScore> selectList(ExamScore examScore);
}
