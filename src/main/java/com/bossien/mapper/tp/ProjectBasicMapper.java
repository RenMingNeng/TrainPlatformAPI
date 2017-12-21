package com.bossien.mapper.tp;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bossien.entity.ProjectBasic;
import com.bossien.entity.ProjectExerciseOrder;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 *
 * projectExerciseOrder 表数据库控制层接口
 *
 */
public interface ProjectBasicMapper extends BaseMapper<ProjectBasic> {

	/**
	 * 获取练习答题记录
	 * @param id
	 * @return
	 */
	ProjectBasic selectOne(@Param(value = "id") String id);

	/**
	 * 更新项目状态
	 * @param param
	 */
	void checkProjectStatus(Map<String, Object> param);
}