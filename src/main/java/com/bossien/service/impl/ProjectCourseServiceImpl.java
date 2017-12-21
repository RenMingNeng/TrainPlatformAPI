package com.bossien.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.entity.ProjectCourse;
import com.bossien.mapper.tp.ProjectCourseMapper;
import com.bossien.service.IProjectCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * x项目课程信息表
 * Created by Administrator on 2017/7/25.
 */
@Service
public class ProjectCourseServiceImpl extends ServiceImpl<ProjectCourseMapper, ProjectCourse> implements IProjectCourseService{

    @Autowired
    private ProjectCourseMapper projectCourseMapper;

    public List<ProjectCourse> selectList(Map<String, Object> params) {
        return projectCourseMapper.selectList(params);
    }

    public Integer selectCountByProjectId(String project_id) {
        return projectCourseMapper.selectCountByProjectId(project_id);
    }

    public List<ProjectCourse> selectByProjectIdAndRoleId(ProjectCourse projectCourse) {

        return projectCourseMapper.selectByProjectIdAndRoleId(projectCourse);
    }

    public List<Map<String, Object>> selectListByParams(Map map) {

        return projectCourseMapper.selectListByParams(map);
    }

}
