package com.bossien.mapper.tp;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bossien.entity.CourseInfo;

import java.util.Map;

/**
 * Created by Administrator on 2017/10/18.
 */
public interface CourseInfoMapper extends BaseMapper<CourseInfo> {
    /**
     * 课程下的总题量
     */
    int selectCourseQuestionCount(Map<String, Object> params);
}
