package com.bossien.service.impl;

import com.bossien.entity.ExerciseAnswers;
import com.bossien.service.IExerciseAnswersService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * question_collection 表数据服务层接口实现类
 *
 */
@Service
public class ExerciseAnswersServiceImpl implements IExerciseAnswersService {
	@Value("${collectionName_exercise_answers}")
	private String collectionName_exercise_answers;
	@Autowired
	private MongoOperations mongoTemplate;

	public List<String> getIds(List<ExerciseAnswers> list) {
		List<String> ids = Lists.newArrayList();
		if(null == list || list.isEmpty()) {
			return ids;
		}
		for(ExerciseAnswers exerciseAnswers : list) {
			ids.add(exerciseAnswers.getQuestion_id());
		}
		return ids;
	}

	public List<ExerciseAnswers> selectList(String project_id, String user_id) {
		//mongo里面查询练习答题数据
		Query query = new Query(new Criteria().andOperator(
				Criteria.where("project_id").is(project_id),
				Criteria.where("user_id").is(user_id)
		));
		return mongoTemplate.find(query, ExerciseAnswers.class, collectionName_exercise_answers);
	}
}