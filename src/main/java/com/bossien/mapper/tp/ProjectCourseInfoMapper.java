package com.bossien.mapper.tp;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bossien.entity.ProjectCourseInfo;
import com.bossien.entity.request.AdminProjectInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/18.
 */
public interface ProjectCourseInfoMapper extends BaseMapper<ProjectCourseInfo> {

    List<Map<String,Object>> selectList(Map map);

    Integer selectCount(Map map);

    /**
     * 获取项目下课程的数量和应修学时
     * @param map
     * @return
     */
    Map<String, Object> selectCourseInfo(Map<String, Object> map);

    /**
     * 查询课程列表
     * @param map
     * @return
     */
    List<Map<String,Object>> selectCourseList(Map<String, Object> map);

    /**
     * 查询课程ids
     * @param map
     * @return
     */
    List<String> selectCourseIds(Map<String, Object> map);

    /**
     * 查询单个
     * @param projectCourseInfo
     * @return
     */
    ProjectCourseInfo selectOne(ProjectCourseInfo projectCourseInfo);

    /**
     * 修改
     * @param projectCourseInfo
     */
    void update(ProjectCourseInfo projectCourseInfo);

    /**
     * 培训详情
     * @param page
     * @param areq
     * @return
     */
    List<Map<Object,Object>> queryTrainProInfo(Page<Map<Object, Object>> page, AdminProjectInfo areq);

    /**
     * 练习详情
     * @param page
     * @param areq
     * @return
     */
    List<Map<Object,Object>> queryExerciseProInfo(Page<Map<Object, Object>> page, AdminProjectInfo areq);

    Integer selectUseCountByCourseId(String courseId);

    ProjectCourseInfo selectOne(Map<String, Object> params);
}
