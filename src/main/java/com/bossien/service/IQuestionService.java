package com.bossien.service;

import com.bossien.entity.Question;

import java.util.List;
import java.util.Map;

/**
 * Created by huangzhaoyong on 2017/7/25.
 */
public interface IQuestionService {

    /**
     * 查询列表
     *
     * @param params
     * @return
     */
    List<Question> selectList(Map<String, Object> params);

    List<Question> selectByIds(List<String> ids);
}
