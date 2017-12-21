package com.bossien.mapper.ap;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bossien.entity.CourseQuestion;

import java.util.List;
import java.util.Map;

/**
 *课程题库关联
 */

public interface CourseQuestionMapper extends BaseMapper<CourseQuestion> {

    /**
     * 查询列表
     *
     * @param params
     * @return
     */
    List<CourseQuestion> selectList(Map<String, Object> params);

    /**
     * 查询试题ids
     *
     * @param params
     * @return
     */
    List<String> selectQuestionIdList(Map<String, Object> params);
}
