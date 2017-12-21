package com.bossien.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.Course;
import com.bossien.entity.request.CourseTypeList;
import com.bossien.entity.request.LatelyStudyCourse;

import java.util.List;
import java.util.Map;

/**
 *
 * ex_course 表数据服务层接口
 *
 */
public interface ICourseService extends IService<Course> {
    /**
     * 根据courseId查询单条课程信息
     * @param intId
     * @return
     */
    Course selectOne(String intId);

    /**
     * 组装课程与附件
     * @param map
     * @return
     */
    Map<String,Object> assemblyCourseAndAttachment(Map<String,Object> map);
    /**
     * 学员首页课程列表查询
     * @param page
     * @param ctl
     * @return
     */
    public Page<Map<Object, Object>> searchAllCourseList(Page<Map<Object, Object>> page, CourseTypeList ctl);

    /**
     * 查询课程分类下所有课程
     * @param page
     * @param ctl
     * @return
     */
    public Page<Map<Object, Object>> queryCourseListByType(Page<Map<Object, Object>> page,CourseTypeList ctl);

    /**
     * 最近学习的课程列表
     * @param latelyStudyCourse
     * @return
     */
    List<Map<Object,Object>> queryLatelyStudyCourseList(LatelyStudyCourse latelyStudyCourse);

    /**
     * 最近学习的课程列表(带分页)
     */
    Page<Map<Object, Object>> queryLatelyStudyCourseList(Page<Map<Object, Object>> page, Map<String, Object> param);

    /**
     * 查询最近上传的课程
     * @param latelyStudyCourse
     * @return
     */
    List<Map<String,Object>> queryLatelyUploadCourseList(LatelyStudyCourse latelyStudyCourse);

    int selectLatelyStudyCount(LatelyStudyCourse latelyStudyCourse);
}
