package com.bossien.service.impl;

import com.bossien.entity.ExamAnswers;
import com.bossien.service.IExamAnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangzhaoyong on 2017/7/25.
 */

@Service
public class ExamAnswersServiceImpl implements IExamAnswersService {
    @Value("${collectionName_exam_answers}")
    private String collectionName_exam_answers;
    @Autowired
    private MongoOperations mongoTemplate;

    public List<ExamAnswers> selectList(String exam_no, String user_id) {
        Query query = new Query(new Criteria().andOperator(
                Criteria.where("exam_no").is(exam_no),
                Criteria.where("user_id").is(user_id)
        ));
        return mongoTemplate.find(query, ExamAnswers.class,
                collectionName_exam_answers);
    }
}
