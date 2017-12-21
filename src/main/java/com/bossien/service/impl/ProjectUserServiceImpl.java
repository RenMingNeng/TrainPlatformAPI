package com.bossien.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.entity.ProjectUser;
import com.bossien.mapper.tp.ProjectUserMapper;
import com.bossien.service.IProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 * project_Basic 表数据服务层实现类
 *
 */
@Service
public class ProjectUserServiceImpl extends ServiceImpl<ProjectUserMapper, ProjectUser> implements IProjectUserService {
    @Autowired
    private ProjectUserMapper projectUserMapper;

    public List<String> selectProjectIds(String user_id) {
        return projectUserMapper.selectProjectIds(user_id);
    }

    public List<String> selectUserIdsByParam(Map map) {
        return projectUserMapper.selectUserIdsByParam(map);
    }

    public ProjectUser selectByProjectIdAndUserId( String projectId, String userId){
        return projectUserMapper.selectByProjectIdAndUserId(projectId,userId);
    }
}
