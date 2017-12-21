package com.bossien.service.impl;


import com.bossien.entity.ProjectStatisticsInfo;
import com.bossien.mapper.tp.ProjectStatisticsInfoMapper;
import com.bossien.service.IProjectInfoService;
import com.bossien.service.IProjectStatisticsInfoService;
import com.bossien.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service
public class ProjectStatisticsInfoServiceImpl implements IProjectStatisticsInfoService {

    @Autowired
    private ProjectStatisticsInfoMapper projectStatisticsInfoMapper;
    @Autowired
    private IProjectInfoService projectInfoService;
    @Autowired
    private IUserService userService;

    @Override
    public ProjectStatisticsInfo statistics(ProjectStatisticsInfo project_statistics_info) {
        return projectStatisticsInfoMapper.statistics(project_statistics_info);
    }
    @Override
    public List<ProjectStatisticsInfo> selectList(ProjectStatisticsInfo project_statistics_info) {
        return null;
    }
    @Override
    public List<ProjectStatisticsInfo> selectListByProjectId(String projectId) {
        return null;
    }
    @Override
    public Integer selectCount(ProjectStatisticsInfo project_statistics_info) {
        return null;
    }
    @Override
    public ProjectStatisticsInfo selectOne(ProjectStatisticsInfo project_statistics_info) {

        return projectStatisticsInfoMapper.selectOne(project_statistics_info);
    }
    @Override
    public ProjectStatisticsInfo selectOne(String projectId, String userId) {
        return null;
    }
    @Override
    public Integer update(ProjectStatisticsInfo projectStatisticsInfo) {

        return projectStatisticsInfoMapper.update(projectStatisticsInfo);
    }
    @Override
    public Integer insert(ProjectStatisticsInfo projectStatisticsInfo) {
        return null;
    }
    @Override
    public int insertBatch(List<ProjectStatisticsInfo> item) {
        return 0;
    }
    @Override
    public List<ProjectStatisticsInfo> selectStatisticsInfoList(ProjectStatisticsInfo project_statistics_info) {
        return null;
    }
    @Override
    public int delete(ProjectStatisticsInfo project_statistics_info) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> selectListMap(Map<String, Object> params) {
        return null;
    }
    @Override
    public List<String> selectIdList(Map<String, Object> params) {
        return null;
    }
    @Override
    public int updateInfo(Map<String, Object> params) {
        return 0;
    }
    @Override
    public int selectTrainCount(Map<String, Object> params) {
        return 0;
    }
    @Override
    public Integer selectQuestions(ProjectStatisticsInfo projectStatisticsInfo) {
        return projectStatisticsInfoMapper.selectQuestions(projectStatisticsInfo);
    }
    @Override
    public Integer selectRankByTotalStudyTime(Map<String, Object> params) {
        return projectStatisticsInfoMapper.selectRankByTotalStudyTime(params);
    }
    @Override
    public double selectTotalClassHour(Map<String, Object> params) {
        return projectStatisticsInfoMapper.selectTotalClassHour(params);
    }
    /**
     * 获取参与培训或者考试人次
     * @param params
     * @return
     */
    @Override
    public int selectJoinTrainOrExamUserCount(Map<String, Object> params){
        //先获取进行中或已结束的并且是含有特定类型项目id
        List<String> projectIds=projectInfoService.selectProjectIds(params);
        if(projectIds.size() == 0){
            return 0 ;
        }
        params.put("projectIds",projectIds);
        //获取该公司下的有效学员ids
        params.put("isValid","1");                                //有效
        List<String> userIds=userService.selectUserIds(params);
        if(userIds.size() == 0){
            return 0;
        }
        params.put("userIds",userIds);
        return projectStatisticsInfoMapper.selectUserCount(params);
    }

    /**
     * 获取培训人数
     * @param params
     * @return
     */
    @Override
    public int selectTrainUserCount(Map<String, Object> params){
        //先获取进行中或已结束的
        List<String> projectIds=projectInfoService.selectProjectIds(params);
        if(projectIds.size()==0){
            return 0;
        }
        params.put("projectIds",projectIds);
        //获取该公司下的有效学员ids
        params.put("isValid","1");                                //有效
        List<String> userIds=userService.selectUserIds(params);
        if(userIds.size() == 0){
            return 0;
        }
        params.put("userIds",userIds);
        params.put("countTrainUser","0");                       //没有学时为0
        params.put("examStatusForTrainUser","1");              //未考试 1
        return projectStatisticsInfoMapper.selectTrainUserCount(params);
    }

    /**
     * 完成培训人次
     * @param params
     * @return
     */
    @Override
    public int selectCompleteTrainUserCount(Map<String, Object> params){
        //先获取进行中或已结束的并且是含有培训的项目id
        List<String> projectIds=projectInfoService.selectProjectIds(params);
        if(projectIds.size()==0){
            return 0;
        }
        params.put("projectIds",projectIds);
        //获取该公司下的有效学员ids
        params.put("isValid","1");                                //有效
        List<String> userIds=userService.selectUserIds(params);
        if(userIds.size() == 0){
            return 0;
        }
        params.put("userIds",userIds);
        params.put("countTrainCompleteYes",60);                       //已修学时大于应修学时
        return projectStatisticsInfoMapper.selectUserCount(params);

    }

    /**
     * 考试合格人次
     * @param params
     * @return
     */
    @Override
    public int selectPassExamUserCount(Map<String, Object> params){
        //先获取进行中或已结束的并且是含有考试的项目id
        List<String> projectIds=projectInfoService.selectProjectIds(params);
        if(projectIds.size()==0){
            return 0;
        }
        params.put("projectIds",projectIds);
        //获取该公司下的有效学员ids
        params.put("isValid","1");                                //有效
        List<String> userIds=userService.selectUserIds(params);
        if(userIds.size() == 0){
            return 0;
        }
        params.put("userIds",userIds);
        params.put("countTrainCompleteYes","");
        params.put("countExamPassYes","2");                         //考试合格
        return projectStatisticsInfoMapper.selectUserCount(params);
    }
    /**
     * 累计学时
     * @param params
     * @return
     */
    @Override
    public double selecttotalClassHour(Map<String, Object> params){
        //先获取进行中或已结束的
        List<String> projectIds=projectInfoService.selectProjectIds(params);
        if(projectIds.size()==0){
            return 0;
        }
        params.put("projectIds",projectIds);
        //获取该公司下的有效学员ids
        List<String> userIds=userService.selectUserIds(params);
        if(userIds.size() == 0){
            return 0;
        }
        params.put("userIds",userIds);
        params.put("isValid","");
        return projectStatisticsInfoMapper.selectTotalClassHours(params);
    }
    /**
     * 年度学时统计
     * @param params
     * @return
     */
    @Override
    public double selecttotalYearClassHour(Map<String, Object> params){
        //先获取进行中或已结束的
        List<String> projectIds=projectInfoService.selectProjectIds(params);
        if(projectIds.size()==0){
            return 0;
        }
        params.put("projectIds",projectIds);
        //获取该公司下的有效学员ids
        List<String> userIds=userService.selectUserIds(params);
        if(userIds.size() == 0){
            return 0;
        }
        params.put("userIds",userIds);
        params.put("isValid","");
        params.put("yearStartTime", Calendar.getInstance().get(Calendar.YEAR)+"-01-01 00:00:00");
        params.put("yearEndTime",Calendar.getInstance().get(Calendar.YEAR)+"-12-31 23:59:59");
        return projectStatisticsInfoMapper.selectTotalClassHours(params);
    }

}
