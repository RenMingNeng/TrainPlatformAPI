package com.bossien.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.bossien.common.util.DateUtils;
import com.bossien.common.util.MapUtil;
import com.bossien.common.util.PropertiesUtils;
import com.bossien.entity.ExamPaperInfo;
import com.bossien.entity.ProjectBasic;
import com.bossien.entity.ProjectStatisticsInfo;
import com.bossien.entity.enumeration.ProjectStatusEnum;
import com.bossien.entity.enumeration.TrainStatueEnum;
import com.bossien.entity.request.AdminProjectInfo;
import com.bossien.entity.request.AdminProjectList;
import com.bossien.entity.request.UserProjectList;
import com.bossien.mapper.tp.*;
import com.bossien.service.*;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/18.
 */
@Service
public class ProjectServiceImpl implements IProjectService{

    private String contain_exercise_type = PropertiesUtils.getValue("contain_exercise_type");
    private String contain_exam_type = PropertiesUtils.getValue("contain_exam_type");
    @Autowired
    private IProjectStatisticsInfoService projectStatisticsInfoService;
    @Autowired
    private IProjectCourseInfoService projectCourseInfoService;
    @Autowired
    private IProjectBasicService projectBasicService;
    @Autowired
    private CompanyProjectMapper companyProjectMapper;
    @Autowired
    private ProjectInfoMapper projectInfoMapper;
    @Autowired
    private ProjectCourseMapper projectCourseMapper;
    @Autowired
    private ProjectCourseInfoMapper projectCourseInfoMapper;
    @Autowired
    private ProjectStatisticsInfoMapper projectStatisticsInfoMapper;
    @Autowired
    private ExamScoreMapper examScoreMapper;
    @Autowired
    private IProjectCourseService projectCourseService;
    @Autowired
    private ExamPaperInfoMapper examPaperInfoMapper;
    @Autowired
    private ProjectBasicMapper projectBasicMapper;

    public List<Map<String, Object>> handleData(List<Map<String, Object>> lisMap, Map<String, Object> param) {
        DecimalFormat df = new DecimalFormat("0.00");
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> listMap1 = new ArrayList<Map<String, Object>>();
        String status = param.get("project_status").toString();
        for (Map<String, Object> map:lisMap) {
            Map<String, Object> map1 = MapUtil.getInstance();
            String  project_train_time = map.get("project_train_time").toString();
            String  beginTime = "";
            String  endTime = "";

            if(!"".equals(project_train_time) &&  null != project_train_time){
                beginTime = project_train_time.substring(0,10)+" 00:00:00";
                endTime = project_train_time.substring(13,23)+" 23:59:59";
            }

            String trainRoleId = "";           //受训角色Id
            String  studyPer = "0.0";            //培训进度
            Double requirementTime = 0.00d;    //应修学时
            Double studyTime = 0.00d;          //已修学时

            //根据project_id 查询受训角色id

            ProjectStatisticsInfo projectStatisticsInfo = projectStatisticsInfoService.statistics(new ProjectStatisticsInfo(map.get("id").toString(),param.get("user_id").toString()));
            if(null != projectStatisticsInfo){
                trainRoleId = projectStatisticsInfo.getRole_id();                                            //受训角色Id
                requirementTime = Double.valueOf(projectStatisticsInfo.getRequirement_studytime());          //应修学时
                studyTime = Double.valueOf(projectStatisticsInfo.getTotal_studytime());                      //已修学时
                if(requirementTime <= 0){
                    if((studyTime)<=0){
                        studyPer = "0.0";
                    }else{
                        studyPer = "1.0";
                    }
                }else{
                    if(studyTime >= (requirementTime*60)){
                        studyPer = "1.0" ;
                    }else {
                        studyPer = df.format(studyTime / (requirementTime * 60));
                    }
                }

            }
            //统计课程数量
            Integer courseNum=projectCourseService.selectCountByProjectId(map.get("id").toString());
            map1.put("project_id",map.get("id").toString());
            map1.put("project_name",map.get("project_name").toString());
            map1.put("train_roleId",trainRoleId);
            map1.put("study_process",studyPer.toString());
            map1.put("begin_time",beginTime);
            map1.put("end_time",endTime);
            map1.put("course_count",courseNum);
            map1.put("study_time",studyTime);
            map1.put("requirement_time",requirementTime.toString());
            if(StringUtils.isNotEmpty(beginTime) && StringUtils.isNotEmpty(endTime)){
                String projectStatus_ = DateUtils.getStatus(beginTime,endTime);
                if(projectStatus_.equals(ProjectStatusEnum.ProjectStatus_3.getValue())){
                    listMap.add(map1);
                }
                if(projectStatus_.equals(ProjectStatusEnum.ProjectStatus_4.getValue())){
                    listMap1.add(map1);
                }
            }
        }
        if(status.equals(ProjectStatusEnum.ProjectStatus_3.getValue())){
            return listMap;
        }

        if(status.equals(ProjectStatusEnum.ProjectStatus_4.getValue())){
            return listMap1;
        }
        return null;
    }

    public List<Map<String, Object>> handleData1(List<Map<String, Object>> lisMap, String user_id) {
        return null;
    }

    @Override
    public List<Map<String, Object>> handleData2(List<Map<String, Object>> lisMap, UserProjectList userProjectList) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> result_ = new ArrayList<Map<String, Object>>();
        String status = userProjectList.getProject_status();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Map<String, Object> objectMap : lisMap) {
            String projectType = objectMap.get("project_type").toString();
            Boolean permission_exercise = false;
            Boolean permission_exam = false;
            Map<String, Object> map1 = MapUtil.getInstance();
            map1.clear();
            map1.put("project_id",objectMap.get("id").toString());
            map1.put("user_id",userProjectList.getUser_id());
            Map<String, Object> params = projectCourseInfoService.selectCourseInfo(map1);
            params.put("project_id",objectMap.get("id").toString());
            params.put("project_name",objectMap.get("project_name").toString());
            if(StringUtils.contains(contain_exercise_type,projectType)){
                permission_exercise = true;
            }
            if(StringUtils.contains(contain_exam_type,projectType)){
                permission_exam = true;
            }
            params.put("permission_exercise",permission_exercise);
            params.put("permission_exam",permission_exam);
            ProjectBasic projectBasic = projectBasicService.selectOne(objectMap.get("id").toString());
            if(null != projectBasic){
                if(StringUtils.isNotEmpty(projectBasic.getProject_exercise_info())){
                    //解析练习项目时间
                    Map<String,Object> map2 = new Gson().fromJson(projectBasic.getProject_exercise_info(),Map.class);
                    params.put("project_exercise_time",objectMap.get("project_exercise_time").toString());
                    params.put("exercise_status", DateUtils.getStatus(map2.get("beginTime").toString(),map2.get("endTime").toString()));
                }
                if(StringUtils.isNotEmpty(projectBasic.getProject_exam_info())){
                    //解析考试项目时间
                    Map<String,Object> map3 = new Gson().fromJson(projectBasic.getProject_exam_info(),Map.class);
                    params.put("exam_status", DateUtils.getStatus(map3.get("beginTime").toString(),map3.get("endTime").toString()));
                    params.put("project_exam_time",objectMap.get("project_exam_time").toString());
                }
            }
            String projectStartTime = objectMap.get("project_start_time").toString().substring(0,19);
            String projectEndTime = objectMap.get("project_end_time").toString().substring(0,19);
            String projectStatus_ = DateUtils.getStatus(projectStartTime,projectEndTime);
            params.put("project_status",projectStatus_);
            if(projectStatus_.equals(ProjectStatusEnum.ProjectStatus_2.getValue()) || projectStatus_.equals(ProjectStatusEnum.ProjectStatus_3.getValue())){
                result.add(params);
            }else if(projectStatus_.equals(ProjectStatusEnum.ProjectStatus_4.getValue())){
                result_.add(params);
            }
        }
        if(status.equals(ProjectStatusEnum.ProjectStatus_3.getValue())){
            return result;
        }

        if(status.equals(ProjectStatusEnum.ProjectStatus_4.getValue())){
            return result_;
        }
        return null;
    }

    public Page<Map<Object, Object>> selectProjectListPage(Page<Map<Object, Object>> page, AdminProjectList apj) {
        Map<String, Object> params=new HashMap<String, Object>();
        Map<String,Object> par=new HashMap<String, Object>();
        List<Map<Object, Object>> list=new ArrayList<Map<Object, Object>>();
        List<String> proList=new ArrayList<String>();
        String proTrainTime="";
        String proExerciseTime="";
        String proExamTime="";
        int usercount=0,coursecount=0;
        int compeleteUserCount=0;
        int examCount=0;
        //找出公司所有的项目id
        List<Map<Object, Object>> pList = companyProjectMapper.selectProjectIds(page, apj);
        if(pList.size()>0){
            for(Map<Object, Object> map:pList){
                if(map.get("project_id")!=null){
                    proList.add(map.get("project_id").toString());
                }
            }
        }

        //如果该公司有项目
        if(proList.size()>0){

            //根据项目id查询项目信息
            apj.setProjectIds(proList);
            list = projectInfoMapper.selectProjectList(apj);

            params.put("companyId", apj.getCompany_id());
            if(list.size()>0){
                for(Map<Object, Object> map:list){

                    //处理查询培训时间
                    if(map.get("project_train_time")!=null){
                        String trainTime=map.get("project_train_time").toString();
                        if("".equals(trainTime) || trainTime==null){
                            map.put("project_train_time", proTrainTime);
                        }else{
                            map.put("project_train_time", trainTime);
                        }
                    }

                    //处理查询练习时间
                    if(map.get("project_exercise_time")!=null){
                        String exerciseTime=map.get("project_exercise_time").toString();
                        if("".equals(exerciseTime) || exerciseTime==null){
                            map.put("project_exercise_time", proExerciseTime);
                        }else{
                            map.put("project_exercise_time", exerciseTime);
                        }
                    }

                    //处理查询考试时间
                    if(map.get("project_exam_time")!=null){
                        String examTime=map.get("project_exam_time").toString();
                        if("".equals(examTime) || examTime==null){
                            map.put("project_exam_time", proExamTime);
                        }else{
                            map.put("project_exam_time", examTime);
                        }
                    }

                    if(map.get("id")!=null){
                        coursecount=projectCourseMapper.selectCountByProjectId(map.get("id").toString());
                        if(map.get("person_count")!=null){
                            map.put("users_count", map.get("person_count"));
                        }else{
                            map.put("users_count", usercount);
                        }
                        map.put("course_count",coursecount);

                        //统计培训完成人数
                        par.put("project_id",map.get("id"));
                        par.put("train_status", TrainStatueEnum.TrainStatue_2.getValue());//培训状态：1未完成2完成
                        compeleteUserCount= projectStatisticsInfoMapper.selectCount(par);
                        map.put("compelete_count", compeleteUserCount);

                    }

                    ProjectBasic pb = projectBasicMapper.selectOne(map.get("id").toString());
                    if(pb!=null && pb.getProject_exam_info()!=null){
                        Gson gson = new Gson();
                        Map<String, Object> examMap = gson.fromJson(pb.getProject_exam_info(), Map.class);
                        if(examMap!=null && examMap.size()>0){
                            if(examMap.get("count")!=null) {
                                examCount = Integer.valueOf(examMap.get("count").toString());
                            }
                        }
                    }
                    //int exam_count=examPaperInfoMapper.selectCount(new ExamPaperInfo(map.get("id").toString(), null, "2", "2"));
                    map.put("exam_count", examCount);
                }

            }
        }
        page.setRecords(list);
        return page;
    }

    public Page<Map<Object, Object>> queryTrainProInfo(Page<Map<Object, Object>> page, AdminProjectInfo api) {
        List<Map<Object, Object>> plist = projectCourseInfoMapper.queryTrainProInfo(page, api);
        page.setRecords(plist);
        return page;
    }

    public Page<Map<Object, Object>> queryExerciseProInfo(Page<Map<Object, Object>> page, AdminProjectInfo api) {
        List<Map<Object, Object>> elist = projectCourseInfoMapper.queryExerciseProInfo(page, api);
        page.setRecords(elist);
        return page;
    }

    public Map<String, Object> queryExamProInfo(AdminProjectInfo api) {
        String examBeginTime="";
        String examEndTime="";
        Map<String, Object> params=new HashMap<String, Object>();
        if(api.getProject_id()!=null){
            params.put("project_id",api.getProject_id());
        }
        if(api.getUser_id()!=null){
            params.put("user_id",api.getUser_id());
        }
        ProjectBasic pb = projectBasicMapper.selectOne(api.getProject_id());
        if(pb!=null && pb.getProject_exam_info()!=null){
            Gson gson = new Gson();
            Map<String, Object> examMap = gson.fromJson(pb.getProject_exam_info(), Map.class);
            if(examMap!=null && examMap.size()>0){
                if(examMap.get("beginTime")!=null && examMap.get("endTime")!=null) {
                    examBeginTime = examMap.get("beginTime").toString();
                    examEndTime = examMap.get("endTime").toString();
                }
            }
        }
        Map<String, Object> map = examScoreMapper.queryExamProInfo(params);
        if(map!=null && map.size()>0){
            map.put("exam_beginTime",examBeginTime);
            map.put("exam_endTime",examEndTime);
        }
        return map;
    }
}
