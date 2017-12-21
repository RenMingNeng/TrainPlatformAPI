package com.bossien.service;


import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.ProjectBasic;

/**
 *
 * project_Basic 表数据服务层接口
 *
 */
public interface IProjectBasicService extends IService<ProjectBasic> {
    /**
     * 查询单条记录
     * @param id
     * @return
     */
    ProjectBasic selectOne(String id);
}
