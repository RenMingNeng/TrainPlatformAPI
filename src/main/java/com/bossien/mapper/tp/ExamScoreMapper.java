package com.bossien.mapper.tp;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bossien.entity.ExamScore;
import com.bossien.entity.Feedback;

import java.util.List;
import java.util.Map;

/**
 *
 * exam_score 表数据库控制层接口
 *
 */
public interface ExamScoreMapper extends BaseMapper<ExamScore> {
	/**
	 * 查询单条记录
	 * @param examScore
	 * @return
	 */
	ExamScore selectOne(ExamScore examScore);

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

	Integer selectPassScoreCount(Map map);

	/**
	 * 考试详情
	 * @param params
	 * @return
	 */
	Map<String,Object> queryExamProInfo(Map<String,Object> params);
}