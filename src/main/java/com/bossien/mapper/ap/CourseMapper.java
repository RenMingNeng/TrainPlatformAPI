package com.bossien.mapper.ap;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bossien.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 根据courseId查询单条课程信息
     * @param intId
     * @return
     */
    Course selectOne(@Param(value = "intId")String intId);

    /**
     * 查询课程集合
     * @param page
     * @param param
     * @return
     */
    List<Map<Object,Object>> selectList(Page<Map<Object, Object>> page, Map<String,Object> param);

    int selectListCount(Map<String,Object> param);

    List<Map<Object,Object>> selectCourseList(Map<String,Object> param);

    /**
     * 最近上传课程集合列表
     * @param param
     * @return
     */
    List<Map<String,Object>> selectLatelyUploadCourse(Map<String,Object> param);

}
