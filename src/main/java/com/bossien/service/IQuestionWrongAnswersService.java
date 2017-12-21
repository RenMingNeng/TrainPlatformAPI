package com.bossien.service;

import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.QuestionWrongAnswers;

import java.util.List;

/**
 *
 * question_wrong_answers 表数据服务层接口
 *
 */
public interface IQuestionWrongAnswersService extends IService<QuestionWrongAnswers> {

    List<String> getIds(List<QuestionWrongAnswers> list);
}