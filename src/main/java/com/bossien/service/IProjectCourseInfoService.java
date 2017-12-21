package com.bossien.service;

import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.ProjectCourseInfo;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface IProjectCourseInfoService extends IService<ProjectCourseInfo> {

    List<Map<String,Object>> selectProjectCourseList(Map map);

    /**
     * 获取项目下课程的数量和应修学时
     * @param map
     * @return
     */
    Map<String, Object> selectCourseInfo(Map<String, Object> map);

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

    Integer selectProjectCourseCount(Map map);
}
