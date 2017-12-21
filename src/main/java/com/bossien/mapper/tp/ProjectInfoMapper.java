package com.bossien.mapper.tp;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bossien.entity.ProjectInfo;
import com.bossien.entity.request.AdminProjectList;

import java.util.List;
import java.util.Map;

/**
 *
 * projectExerciseOrder 表数据库控制层接口
 *
 */
public interface ProjectInfoMapper extends BaseMapper<ProjectInfo> {

	/**
	 * 通过项目id查询项目信息（项目详情）
	 * @param id
	 * @return
	 */
	ProjectInfo selectProjectInfoById(String id);

	/**
	 * 获取项目集合
	 * @param param
	 * @return
	 */
	List<Map<String,Object>> selectList(Map<String,Object> param);

	/**
	 * 获取总条数
	 * @param param
	 * @return
	 */
	Integer selectCount(Map<String,Object> param);

	Integer selectCountByParams(Map map);

    List<Map<String,Object>> selectListByParams(Map map);

	/**
	 * 分页查询所有项目列表
	 * @param page
	 * @param apj
	 * @return
	 */
	List<Map<Object,Object>> selectProjectListPage(Page<Map<Object, Object>> page,AdminProjectList apj);

	/**
	 * 查询所有项目列表(不带分页)
	 * @param apj
	 * @return
	 */
	List<Map<Object,Object>> selectProjectList(AdminProjectList apj);
	/**
	 * 根据状态和类型查询projectIds
	 * @param params
	 * @return
	 */
	List<String> selectProjectIds(Map<String, Object> params);

	/**
	 * 获取项目的状态值
	 * @param param
	 * @return
	 */
	List<String> getIdsByStatus(Map<String, Object> param);

	/**
	 * 更新项目状态
	 * @param param
	 */
	void checkProjectStatus(Map<String, Object> param);
}
