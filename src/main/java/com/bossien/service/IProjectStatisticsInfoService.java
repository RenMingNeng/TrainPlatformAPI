package com.bossien.service;

import com.bossien.entity.ProjectStatisticsInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/24.
 */
public interface IProjectStatisticsInfoService {


    ProjectStatisticsInfo statistics(ProjectStatisticsInfo project_statistics_info);

    /**
     * 查询列表
     * @param project_statistics_info
     * @return
     */
    List<ProjectStatisticsInfo> selectList(ProjectStatisticsInfo project_statistics_info);

    /**
     * 根据项目id查询
     * @param projectId
     * @return
     */
    List<ProjectStatisticsInfo> selectListByProjectId(String projectId);

    /**
     * 统计
     * @param project_statistics_info
     * @return
     */
    Integer selectCount(ProjectStatisticsInfo project_statistics_info);

    ProjectStatisticsInfo selectOne(ProjectStatisticsInfo project_statistics_info);
    ProjectStatisticsInfo selectOne(String projectId, String userId);

    Integer update(ProjectStatisticsInfo projectStatisticsInfo);

    Integer insert(ProjectStatisticsInfo projectStatisticsInfo);

    /**
     *批量插入个人信息统计表
     * @param item
     * @return
     */
    int insertBatch(List<ProjectStatisticsInfo> item);

    /**
     * 根据项目id查询人员统计信息集合
     * @param project_statistics_info
     * @return
     */
    List<ProjectStatisticsInfo> selectStatisticsInfoList(ProjectStatisticsInfo project_statistics_info);

    /**
     * 根据属性删除信息
     * @param project_statistics_info
     * @return
     */
    int delete(ProjectStatisticsInfo project_statistics_info);

    /**
     * 获取参与培训人次
     * @param params
     * @return
     */
    int selectJoinTrainOrExamUserCount(Map<String, Object> params);
    /**
     * 获取培训人数
     * @param params
     * @return
     */
    int selectTrainUserCount(Map<String, Object> params);

    /**
     * 完成培训人次
     * @param params
     * @return
     */
    int selectCompleteTrainUserCount(Map<String, Object> params);
    /**
     * 考试合格人次
     * @param params
     * @return
     */
    int selectPassExamUserCount(Map<String, Object> params);

    /**
     * 累计学时
     * @param params
     * @return
     */
    double selecttotalClassHour(Map<String, Object> params);

    /**
     * 年度学时统计
     * @param params
     * @return
     */
   double selecttotalYearClassHour(Map<String, Object> params);


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
     * 查询试题总题量
     * @param projectStatisticsInfo
     * @return
     */
    Integer selectQuestions(ProjectStatisticsInfo projectStatisticsInfo);
    /**
     * 通过每个人的总学时排名
     * @param params
     * @return
     */
    Integer selectRankByTotalStudyTime(Map<String, Object> params);

    /**
     * 个人中心的我的排行学时查询
     * @param params
     * @return
     */
    double selectTotalClassHour(Map<String, Object> params);
}
