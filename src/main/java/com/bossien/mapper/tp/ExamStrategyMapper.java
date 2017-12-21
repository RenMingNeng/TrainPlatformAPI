package com.bossien.mapper.tp;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bossien.entity.ExamStrategy;

/**
 *
 * exam_strategy 表数据库控制层接口
 *
 */
public interface ExamStrategyMapper extends BaseMapper<ExamStrategy> {
	/**
	 * 查询单条记录
	 * @param examStrategy
	 * @return
	 */
	ExamStrategy selectOne(ExamStrategy examStrategy);
}