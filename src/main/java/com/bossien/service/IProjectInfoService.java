package com.bossien.service;


import com.bossien.entity.ProjectInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/25.
 */
public interface IProjectInfoService {


    Integer selectCount(Map<String,Object> param);

    List<Map<String,Object>>  selectList(Map<String,Object> param);

    /**
     * 通过项目id查询项目信息（项目详情）
     * @param id
     * @return
     */
    ProjectInfo selectProjectInfoById(String id);


    /**
     * 项目详细信息保存
     * @param params
     */
    void save(Map<String, Object> params);

    /**
     * 修改项目详情
     * @param projectInfo
     * @return
     */
    int update(ProjectInfo projectInfo);

    /**
     * 根据projectId删除
     * @param projectInfo
     * @return
     */
    int delete(ProjectInfo projectInfo);

    /**
     * 发布
     * @param params
     */
    void publish(Map<String, Object> params);


    Integer selectCountByParams(Map map);

    List<Map<String,Object>> selectListByParams(Map map);

    /**
     * 查询学员项目列表
     * @param map
     * @return
     */
    List<Map<String,Object>> selectListStudentProjectByParams(Map<String, Object> map, String user_id);

    /**
     * 根据项目id查看项目信息
     */
    Map selectProjectById(String project_id);

    /**
     * 根据状态和类型查询projectIds
     * @param params
     * @return
     */
    List<String> selectProjectIds(Map<String, Object> params);

    /**
     * 检测项目状态
     * @param params
     * @return
     */
    Integer checkProjectStatus(Map<String, Object> params);
}
