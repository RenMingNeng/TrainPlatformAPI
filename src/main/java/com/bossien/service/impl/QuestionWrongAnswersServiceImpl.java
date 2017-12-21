package com.bossien.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.entity.QuestionWrongAnswers;
import com.bossien.mapper.tp.QuestionWrongAnswersMapper;
import com.bossien.service.IQuestionWrongAnswersService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * question_wrong_answers 表数据服务层接口实现类
 *
 */
@Service
public class QuestionWrongAnswersServiceImpl extends ServiceImpl<QuestionWrongAnswersMapper, QuestionWrongAnswers> implements IQuestionWrongAnswersService {

	public List<String> getIds(List<QuestionWrongAnswers> list) {
		List<String> ids = Lists.newArrayList();
		if(null == list || list.isEmpty()) {
			return ids;
		}
		for(QuestionWrongAnswers questionWrongAnswers : list) {
			ids.add(questionWrongAnswers.getQuestion_id());
		}
		return ids;
	}
}