package com.bossien.mapper.tp;

import com.baomidou.mybatisplus.plugins.Page;
import com.bossien.entity.ProjectStatisticsInfo;
import com.bossien.entity.request.AdminProjectInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProjectStatisticsInfoMapper {


	ProjectStatisticsInfo statistics(ProjectStatisticsInfo project_statistics_info);

	/**
	 * 查询列表
	 * @param project_statistics_info
	 * @return
	 */
	List<ProjectStatisticsInfo> selectList(ProjectStatisticsInfo project_statistics_info);

	/**
	 * 查询用户、学时两个字段列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> selectUserStudyTimeList(Map<String, Object> params);

	/**
	 * 查询试题总题量
	 * @param projectStatisticsInfo
	 * @return
	 */
	Integer selectQuestions(ProjectStatisticsInfo projectStatisticsInfo);

	/**
	 * 统计
	 * @param params
	 * @return
	 */
	Integer selectCount(Map<String, Object> params);

    ProjectStatisticsInfo selectOne(ProjectStatisticsInfo project_statistics_info);

    Integer update(ProjectStatisticsInfo projectStatisticsInfo);

	Integer insert(ProjectStatisticsInfo projectStatisticsInfo);

	/**
	 *批量插入个人信息统计表
	 * @param item
	 * @return
	 */
	int insertBatch(List<Map<String, Object>> item);

	/**
	 * 根据项目id查询人员统计信息集合
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> selectStatisticsInfoList(Map<String, Object> param);

	/**
	 * 根据属性删除信息
	 * @param params
	 * @return
	 */
	int delete(Map<String, Object> params);

	/**
	 * 获取人次
	 * @param params
	 * @return
	 */
	int selectUserCount(Map<String, Object> params);

	/**
	 * 获取培训人数
	 * @param params
	 * @return
	 */
	int selectTrainUserCount(Map<String, Object> params);

	/**
	 * 累计学时统计
	 * @param params
	 * @return
	 */
	Double selectTotalClassHour(Map<String, Object> params);
	/**
	 * 累计学时统计
	 * @param params
	 * @return
	 */
	Double selectTotalClassHours(Map<String, Object> params);
	/**
	 * 根据属性查询学时
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> selectListMap(Map<String, Object> params);

	/**
	 * 根据项目Id和角色Ids查询id集合
	 * @param params
	 * @return
	 */
	List<String> selectIdList(Map<String, Object> params);

	/**
	 * 高级设置中修改项目个人档案详细信息
	 * @param params
	 * @return
	 */
	int updateInfo(Map<String, Object> params);

	/**
	 * 查询用户培训次数
	 * @param params
	 * @return
	 */
	int selectTrainCount(Map<String, Object> params);

	/**
	 * 通过每个人的总学时排名
	 * @param params
	 * @return
	 */
	Integer selectRankByTotalStudyTime(Map<String, Object> params);


    Integer selectUserCountByParam(Map map);
}