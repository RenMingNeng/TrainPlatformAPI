package com.bossien.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.entity.QuestionCollection;
import com.bossien.mapper.tp.QuestionCollectionMapper;
import com.bossien.service.IQuestionCollectionService;
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
public class QuestionCollectionServiceImpl extends ServiceImpl<QuestionCollectionMapper, QuestionCollection> implements IQuestionCollectionService {
	@Value("${mongo_db_question_collection}")
	private String collectionName_question_collection;
	@Autowired
	private MongoOperations mongoTemplate;

	public List<String> getIds(List<QuestionCollection> list) {
		List<String> ids = Lists.newArrayList();
		if(null == list || list.isEmpty()) {
			return ids;
		}
		for(QuestionCollection questionCollection : list) {
			ids.add(questionCollection.getQuestion_id());
		}
		return ids;
	}

	public List<QuestionCollection> selectList(String project_id, String user_id) {
		//mongo里面查询收藏数据
		Query query = new Query(new Criteria().andOperator(
				Criteria.where("project_id").is(project_id),
				Criteria.where("user_id").is(user_id)
		));
		return mongoTemplate.find(query, QuestionCollection.class,
				collectionName_question_collection);
	}
}