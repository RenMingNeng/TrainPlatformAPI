package com.bossien.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.common.util.MapUtil;
import com.bossien.entity.ProjectBasic;
import com.bossien.entity.ProjectDepartment;
import com.bossien.entity.ProjectInfo;
import com.bossien.entity.ProjectStatisticsInfo;
import com.bossien.entity.enumeration.ProjectStatusEnum;
import com.bossien.mapper.tp.ProjectBasicMapper;
import com.bossien.mapper.tp.ProjectCourseMapper;
import com.bossien.mapper.tp.ProjectInfoMapper;
import com.bossien.mapper.tp.ProjectStatisticsInfoMapper;
import com.bossien.service.ICompanyProjectService;
import com.bossien.service.IProjectDepartmentService;
import com.bossien.service.IProjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 项目信息表
 * Created by Administrator on 2017/7/25.
 */
@Service(value = "projectInfoService")
public class ProjectInfoServiceImpl extends ServiceImpl<ProjectInfoMapper, ProjectInfo> implements IProjectInfoService {

    @Autowired
    private ProjectInfoMapper projectInfoMapper;
    @Autowired
    private ProjectCourseMapper projectCourseMapper;
    @Autowired
    private ProjectStatisticsInfoMapper projectStatisticsInfoMapper;
    @Autowired
    private IProjectDepartmentService projectDepartmentService;
    @Autowired
    private ICompanyProjectService companyProjectService;
    @Autowired
    private ProjectBasicMapper projectBasicMapper;

    public Integer selectCount(Map<String,Object> param) {

        return projectInfoMapper.selectCount(param);
    }

    public List<Map<String, Object>>  selectList(Map<String,Object> param)
    {
        return projectInfoMapper.selectList(param);
    }

    public ProjectInfo selectProjectInfoById(String id) {
        return projectInfoMapper.selectProjectInfoById(id);
    }

    public void save(Map<String, Object> params) {

    }

    public int update(ProjectInfo projectInfo) {
        return 0;
    }

    public int delete(ProjectInfo projectInfo) {
        return 0;
    }

    public void publish(Map<String, Object> params) {

    }

    public Integer selectCountByParams(Map map) {
        return projectInfoMapper.selectCountByParams(map);
    }

    public List<Map<String, Object>> selectListByParams(Map map) {
        return projectInfoMapper.selectListByParams(map);
    }

    public List<Map<String, Object>> selectListStudentProjectByParams(Map<String, Object> map, String user_id) {
        List<Map<String, Object>> projects = projectInfoMapper.selectListByParams(map);
        for(Map<String, Object> data: projects){
            Object id = data.get("id");
            //课程数量
            data.put("course_count", projectCourseMapper.selectProjectCourseCount(id.toString()));
            //完成培训学员数量
            Map<String, Object> params = MapUtil.getInstance();
            params.put("project_id", id);
            params.put("train_status", "2");//完成
            data.put("complete_count", projectStatisticsInfoMapper.selectCount(params));

            //requestTime 应修学时
            //alreadyStudyTime 已修学时

            ProjectStatisticsInfo projectStatisticsInfo = projectStatisticsInfoMapper.selectOne(
                    new ProjectStatisticsInfo(id.toString(), user_id));

            Long request_time = 0L;
            Long already_study_time = 0L;
            if(null != projectStatisticsInfo){
                request_time = projectStatisticsInfo.getRequirement_studytime();
                already_study_time = projectStatisticsInfo.getTotal_studytime();
            }
            data.put("request_time", request_time);
            data.put("already_study_time", already_study_time);
        }
        return projects;
    }


    public Map selectProjectById(String id) {
        Map map = MapUtil.getInstance();
        ProjectInfo projectInfo = projectInfoMapper.selectProjectInfoById(id);
        if(null == projectInfo) return map;
        map.put("project_id",projectInfo.getId());
        map.put("project_name",projectInfo.getProject_name());
        map.put("project_start_time",projectInfo.getProject_start_time().substring(0,19));
        map.put("project_end_time",projectInfo.getProject_end_time().substring(0,19));
        map.put("project_train_time",projectInfo.getProject_train_time()==null?"":projectInfo.getProject_train_time());
        map.put("project_exercise_time",projectInfo.getProject_exercise_time()==null?"":projectInfo.getProject_exercise_time());
        map.put("project_exam_time",projectInfo.getProject_exam_time()==null?"":projectInfo.getProject_exam_time());
        // 培训部门集合
        List<ProjectDepartment> department_list =  projectDepartmentService.selectListByProjectId(id);
        map.put("department_list",department_list);
        return map;
    }
    /**
     * 根据状态和类型查询projectIds
     * @param params
     * @return
     */
    @Override
    public List<String> selectProjectIds(Map<String, Object> params){
        List<String> projectIds=companyProjectService.selectProjectIdsByCompanyId(params.get("company_id").toString());
        params.put("projectIds",projectIds);
        return projectInfoMapper.selectProjectIds(params);
    }

    @Override
    public Integer checkProjectStatus(Map<String, Object> params) {
        Integer count = 0;
        String companyId = params.get("company_id").toString();
        Map<String, Object> param = new ConcurrentHashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(new Date());
        List<String> list = companyProjectService.selectProjectIdsByCompanyId(companyId);
        if(null == list || list.isEmpty()){
            return count;
        }
        param.put("projectIds",list);
        param.put("projectStatus", ProjectStatusEnum.ProjectStatus_2.getValue());        // 未开始的项目
        param.put("currentTime",currentTime);                                           // 当前时间
        // 获取当前单位下未开始并且需要更新状态的项目id集合
        List<String> projectIds = projectInfoMapper.getIdsByStatus(param);
        if( null != projectIds && projectIds.size() > 0 ){
            param.clear();
            param.put("projectIds",projectIds);
            param.put("projectStatus",ProjectStatusEnum.ProjectStatus_3.getValue());
            // 更新未开始的项目状态projectInfo
            projectInfoMapper.checkProjectStatus(param);
            // 更新未开始的项目状态projectBase
            projectBasicMapper.checkProjectStatus(param);
            count = projectIds.size();
        }

        param.put("projectStatus", ProjectStatusEnum.ProjectStatus_3.getValue());        // 进行中的项目
        param.put("currentTime_1",currentTime);                                         // 当前时间
        param.put("currentTime","");
        // 获取当前单位下进行中并且需要更新状态的项目id集合
        List<String> IngProjectIds = projectInfoMapper.getIdsByStatus(param);
        if( null != IngProjectIds && IngProjectIds.size() > 0 ){
            param.clear();
            param.put("projectIds",IngProjectIds);
            param.put("projectStatus",ProjectStatusEnum.ProjectStatus_4.getValue());
            // 更新进行中的项目状态projectInfo
            projectInfoMapper.checkProjectStatus(param);
            // 更新进行中的项目状态projectBase
            projectBasicMapper.checkProjectStatus(param);
            count = IngProjectIds.size();
        }
        return count;
    }

}
