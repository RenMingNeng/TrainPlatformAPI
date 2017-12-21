package com.bossien.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.entity.ProjectExerciseOrder;
import com.bossien.mapper.tp.ProjectExerciseOrderMapper;
import com.bossien.service.IProjectExerciseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 * user 表数据服务层接口实现类
 *
 */
@Service
public class ProjectExerciseOrderServiceImpl extends ServiceImpl<ProjectExerciseOrderMapper, ProjectExerciseOrder> implements IProjectExerciseOrderService {
	@Autowired
	private ProjectExerciseOrderMapper projectExerciseOrderMapper;

	public ProjectExerciseOrder selectOne(ProjectExerciseOrder projectExerciseOrder) {
		return projectExerciseOrderMapper.selectOne(projectExerciseOrder);
	}

	public List<Map<String, Object>> selectUserRank(Page<Map<Object, Object>> page,Map<String, Object> params) {
		return projectExerciseOrderMapper.selectUserRank(page,params);
	}

	public Integer selectUserRankCount(Map<String, Object> params) {
		return projectExerciseOrderMapper.selectUserRankCount(params);
	}

	public void update(ProjectExerciseOrder projectExerciseOrder) {

		projectExerciseOrderMapper.update(projectExerciseOrder);
	}
}