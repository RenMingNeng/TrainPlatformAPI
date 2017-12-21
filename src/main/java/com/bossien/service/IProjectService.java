package com.bossien.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.bossien.entity.request.AdminProjectInfo;
import com.bossien.entity.request.AdminProjectList;
import com.bossien.entity.request.UserProjectList;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/18.
 */
public interface IProjectService {

    /**
     * 组装所需数据
     * @param lisMap
     * @param param
     * @return
     */
    List<Map<String, Object>>  handleData (List<Map<String, Object>> lisMap,Map<String,Object> param);

    /**
     * 组装所需数据
     * @param lisMap
     * @param user_id
     * @return
     */
    List<Map<String, Object>>  handleData1 (List<Map<String, Object>> lisMap,String user_id);

    /**
     * 组装所需数据
     * @param lisMap
     * @param userProjectList
     * @return
     */
    List<Map<String, Object>>  handleData2 (List<Map<String, Object>> lisMap, UserProjectList userProjectList);

    /**
     * 查询单位下所有项目列表
     * @param page
     * @param apj
     * @return
     */
    public Page<Map<Object, Object>> selectProjectListPage(Page<Map<Object, Object>> page, AdminProjectList apj);

    /**
     * 培训项目详情
     * @param page
     * @param api
     * @return
     */
    Page<Map<Object, Object>> queryTrainProInfo(Page<Map<Object, Object>> page,AdminProjectInfo api);

    /**
     * 练习详情
     * @param page
     * @param api
     * @return
     */
    Page<Map<Object, Object>> queryExerciseProInfo(Page<Map<Object, Object>> page,AdminProjectInfo api);

    /**
     * 考试详情
     * @param api
     * @return
     */
    Map<String,Object> queryExamProInfo(AdminProjectInfo api);
}
