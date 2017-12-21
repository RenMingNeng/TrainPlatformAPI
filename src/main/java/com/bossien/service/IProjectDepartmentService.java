package com.bossien.service;

import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.Department;
import com.bossien.entity.ProjectDepartment;

import java.util.List;
import java.util.Map;

/**
 *
 * project_department 表数据服务层接口
 *
 */
public interface IProjectDepartmentService extends IService<ProjectDepartment> {

    Integer selectCountByParams(Map map);

    List<Map<String,Object>> selectListByParams(Map map);

    Integer studyProgressCount(Map map);

    List<Map<String,Object>> studyProgressList(Map map);

    List<ProjectDepartment> selectListByProjectId(String id);
}