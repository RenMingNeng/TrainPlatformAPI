package com.bossien.mapper.tp;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bossien.entity.ProjectUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProjectUserMapper extends BaseMapper<ProjectUser> {
	/**
	 * 获取练习答题记录
	 * @param user_id
	 * @return
	 */
	List<String> selectProjectIds(@Param(value = "user_id") String user_id);

	/**
	 * 根据projectId和UserId查询角色Id
	 * @param projectId
	 * @param userId
	 * @return
	 */
	ProjectUser selectByProjectIdAndUserId(@Param("projectId") String projectId, @Param("userId") String userId);

	Integer selectDeptUserCount(Map map);

	List<String> selectUserIdsByParam(Map map);
}