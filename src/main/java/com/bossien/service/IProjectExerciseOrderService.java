package com.bossien.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.ProjectExerciseOrder;

import java.util.List;
import java.util.Map;

/**
 *
 * projectExerciseOrder 表数据服务层接口
 *
 */
public interface IProjectExerciseOrderService extends IService<ProjectExerciseOrder> {

    /**
     * 获取练习答题记录
     * @param projectExerciseOrder
     * @return
     */
    ProjectExerciseOrder selectOne(ProjectExerciseOrder projectExerciseOrder);

    /**
     * 任务排行榜
     * @return
     */
    List<Map<String,Object>> selectUserRank (Page<Map<Object, Object>> page,Map<String, Object> params);

    /**
     * 任务排行榜数量
     * @return
     */
    Integer selectUserRankCount(Map<String, Object> params);

    /**
     * 修改
     * @param projectExerciseOrder
     */
    void update(ProjectExerciseOrder projectExerciseOrder);
}