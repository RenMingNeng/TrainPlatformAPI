package com.bossien.service;

import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.CourseInfo;

import java.util.Map;

/**
 *
 */
public interface ICourseInfoService extends IService<CourseInfo> {


    /**
     * 课程下的总题量
     *
     * @param params
     * @return
     */
    int selectCourseQuestionCount(Map<String, Object> params);

}
