package com.bossien.service;


import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.ExamStrategy;

/**
 *
 * exam_strategy 表数据服务层接口
 *
 */
public interface IExamStrategyService  extends IService<ExamStrategy>{
    /**
     * 查询单条记录
     * @param examStrategy
     * @return
     */
    ExamStrategy selectOne(ExamStrategy examStrategy);
}
