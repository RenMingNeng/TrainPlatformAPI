package com.bossien.service.impl;

import com.bossien.entity.Question;
import com.bossien.mapper.ap.QuestionMapper;
import com.bossien.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by huangzhaoyong on 2017/7/25.
 */

@Service
public class QuestionServiceImpl implements IQuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    public List<Question> selectList(Map<String, Object> params) {

        return questionMapper.selectList(params);
    }

    public List<Question> selectByIds(List<String> ids) {
        if(null == ids || ids.isEmpty())
            return null;
        return questionMapper.selectByIds(ids);
    }
}
