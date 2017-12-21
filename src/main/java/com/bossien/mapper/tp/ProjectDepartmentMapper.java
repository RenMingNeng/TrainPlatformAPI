package com.bossien.mapper.tp;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bossien.entity.ProjectDepartment;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/18.
 */
public interface ProjectDepartmentMapper extends BaseMapper<ProjectDepartment> {


    Integer selectCountByParams(Map map);

    List<Map<String,Object>> selectListByParams(Map map);

    List<ProjectDepartment> selectListByProjectId(Map map);
}
