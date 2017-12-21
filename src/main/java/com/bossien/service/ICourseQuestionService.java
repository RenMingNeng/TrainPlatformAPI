package com.bossien.service;

import com.bossien.entity.Question;

import java.util.List;
import java.util.Map;

/**
 * Created by huangzhaoyong on 2017/7/25.
 */
public interface ICourseQuestionService {

    /**
     * 根据项目id+用户id查询  map<question_id, course_id>
     *
     * @param project_id
     * @param user_id
     * @return
     */
    Map<String, String> selectCourseIdByQuestionId(String project_id, String user_id);

    /**
     * 查询题库集合
     *
     * @param project_id
     * @param user_id
     * @return
     */
    List<Question> selectQuestionByProjectAndUserId(String project_id, String user_id);
}
