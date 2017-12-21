package com.bossien.service;


import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.ProjectUser;

import java.util.List;
import java.util.Map;

/**
 *
 * project_user 表数据服务层接口
 *
 */
public interface IProjectUserService extends IService<ProjectUser> {
    /**
     * 查询单条记录
     * @param user_id
     * @return
     */
    List<String> selectProjectIds(String user_id);

    List<String> selectUserIdsByParam(Map map);

    /**
     * 根据projectId和UserId查询角色Id
     * @param projectId
     * @param userId
     * @return
     */
    ProjectUser selectByProjectIdAndUserId( String projectId, String userId);
}
