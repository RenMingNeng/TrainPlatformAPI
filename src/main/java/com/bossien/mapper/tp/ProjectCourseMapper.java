package com.bossien.mapper.tp;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bossien.entity.ProjectCourse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 项目信息
 * Created by Administrator on 2017/7/25.
 */
@Repository
public interface ProjectCourseMapper extends BaseMapper<ProjectCourse> {

    List<ProjectCourse> selectList(Map<String,Object> params);

    /**
     * 通过project_id查询课程数量
     * @param project_id
     * @return
     */
    Integer selectCountByProjectId(@Param(value = "project_id") String project_id);
    /**
     * 查询项目课程ids集合
     * @param projectCourse
     * @return
     */
    List<String > selectCourseIds(ProjectCourse projectCourse);

    /**
     * 根据projectId和roleId查询相关课程
     * @param projectCourse
     * @return
     */
    List<ProjectCourse> selectByProjectIdAndRoleId(ProjectCourse projectCourse);

    List<Map<String,Object>> selectListByParams(Map map);

    /**
     * 查询项目课程总条数
     * @param project_id
     * @return
     */
    int selectProjectCourseCount(String project_id);
}
