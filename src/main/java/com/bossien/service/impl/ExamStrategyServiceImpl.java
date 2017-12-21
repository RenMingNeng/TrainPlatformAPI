package com.bossien.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.entity.ExamStrategy;
import com.bossien.mapper.tp.ExamStrategyMapper;
import com.bossien.service.IExamStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/10/18.
 */
@Service
public class ExamStrategyServiceImpl extends ServiceImpl<ExamStrategyMapper, ExamStrategy> implements IExamStrategyService {

    @Autowired
    private ExamStrategyMapper examStrategyMapper;

    public ExamStrategy selectOne(ExamStrategy examStrategy) {
        return examStrategyMapper.selectOne(examStrategy);
    }
}
