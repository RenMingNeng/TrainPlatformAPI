package com.bossien.service;

import com.bossien.entity.ExamAnswers;

import java.util.List;

/**
 * Created by huangzhaoyong on 2017/7/25.
 */
public interface IExamAnswersService {

    /**
     * 查询列表
     * @param exam_no
     * @param user_id
     * @return
     */
    List<ExamAnswers> selectList(String exam_no, String user_id);
}
