package com.bossien.service;

import com.bossien.entity.ExamQuestion;
import com.bossien.entity.Question;

import java.util.List;

/**
 * Created by huangzhaoyong on 2017/7/25.
 */
public interface IExamQuestionService {

    /**
     * 创建考卷
     * @param project_id
     * @param user_id
     * @param exam_type
     * @return
     */
    String checkPaper(String project_id, String user_id, String exam_type);

    /**
     * 创建考卷
     * @param project_id
     * @param user_id
     * @param exam_type
     * @return
     */
    List<Question> createPaper(String project_id, String exam_no, String user_id, String user_name, String exam_type);

    /**
     * 查询列表
     * @param examQuestion
     * @return
     */
    List<String> selectList(ExamQuestion examQuestion);
}
