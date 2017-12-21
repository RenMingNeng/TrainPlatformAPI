package com.bossien.service;


import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.ProjectCourse;

import java.util.List;
import java.util.Map;

/**
 *
 * project_course 表数据服务层接口
 *
 */
public interface IProjectCourseService extends IService<ProjectCourse> {

    List<ProjectCourse> selectList(Map<String,Object> params);

    /**
     * 通过project_id查询课程数量
     * @param project_id
     * @return
     */
    Integer selectCountByProjectId( String project_id);

    /**
     * 根据projectId和roleId查询相关课程
     * @param projectCourse
     * @return
     */
    List<ProjectCourse> selectByProjectIdAndRoleId(ProjectCourse projectCourse);

    List<Map<String,Object>> selectListByParams(Map map);
}
