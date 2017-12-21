package com.bossien.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.entity.*;
import com.bossien.entity.enumeration.ClassHourSourceEmun;
import com.bossien.entity.request.SaveVideoPosition;
import com.bossien.mapper.tp.*;
import com.bossien.service.IVideoPositionService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/10/18.
 */
@Service
public class VideoPositionServiceImpl extends ServiceImpl<VideoPositionMapper, VideoPosition> implements IVideoPositionService {
    public static final Logger logger = LoggerFactory.getLogger(VideoPositionServiceImpl.class);
    @Autowired
    private VideoPositionMapper videoPositionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProjectCourseInfoMapper projectCourseInfoMapper;
    @Autowired
    private ProjectStatisticsInfoMapper projectStatisticsInfoMapper;
    @Autowired
    private PersonDossierMapper personDossierMapper;
    @Autowired
    private MongoOperations mongoTemplate;

    // 操作mongodb的集合名,等同于mysql中的表
    private final String collectionName_class_hours = "class_hours";
    private final String collectionName_person_dossier = "person_dossier";

    public VideoPosition selectOne(VideoPosition videoPosition) {
        return videoPositionMapper.selectOne(videoPosition);
    }

    public int saveUserVideoInfo(SaveVideoPosition svp) {
        int result=0;
        String user_name="";
        Integer study_time=svp.getStudy_time();
        User user=new User();
        user.setId(svp.getUser_id());
        user=userMapper.selectOne(user);
        if(user!=null){
            user_name=user.getUser_name();
        }
        VideoPosition videoPosition=new VideoPosition();
        //插入视频播放位置信息
        List<Map<String, Object>> v_map= svp.getWatch_positions();
       if(v_map.size()>0 && !v_map.isEmpty()){
           for(Map<String, Object> map:v_map){
               videoPosition.setUser_id(svp.getUser_id());
               videoPosition.setCourse_id(svp.getCourse_id());
               videoPosition.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
               videoPosition.setOper_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
               if(map.get("file_id")!=null){
                   videoPosition.setVideo_id(map.get("file_id").toString());
               }
               if(map.get("last_position")!=null){
                   videoPosition.setLast_position(Integer.valueOf(map.get("last_position").toString()));
               }else{
                   videoPosition.setLast_position(0);
               }
               VideoPosition vdp = videoPositionMapper.selectOne(videoPosition);
               if(vdp==null){
                   videoPosition.setId(UUID.randomUUID().toString());
                   result=videoPositionMapper.insert(videoPosition);
               }else{//如果存在记录就修改
                   videoPosition.setId(vdp.getId());
                   result=videoPositionMapper.update(videoPosition);
               }

           }//for
       }

       //有项目就更新学时，没有则不处理
       if(svp.getProject_id()!=null){
           // 插入学时记录
           ClassHours classHours = new ClassHours();
           classHours.setProject_id(svp.getProject_id());
           classHours.setCourse_id(svp.getCourse_id());
           classHours.setUser_id(svp.getUser_id());
           classHours.setSource(ClassHourSourceEmun.CLASS_HOUR_SOURCE_1.getValue());
           classHours.setStudy_time(Long.valueOf(svp.getStudy_time()));
           classHours.setCreate_user(user_name);
           classHours.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
           mongoTemplate.insert(classHours, collectionName_class_hours);


           // 插入项目课程信息 project_course_info
           Map<String, Object> params = new ConcurrentHashMap<String, Object>();
           params.clear();
           params.put("project_id", svp.getProject_id());
           params.put("course_id", svp.getCourse_id());
           params.put("user_id", svp.getUser_id());
           ProjectCourseInfo projectCourseInfo = projectCourseInfoMapper.selectOne(params);
           ProjectCourseInfo pcInfo=new ProjectCourseInfo();
           if(null != projectCourseInfo) {
               pcInfo.setId(projectCourseInfo.getId());
               pcInfo.setProject_id(projectCourseInfo.getProject_id());
               //判断应修学时和已修学时，来更新finishStatus培训状态（-1未完成1完成）
               Long requirementStudytime = projectCourseInfo.getRequirement_studytime();
               Long totalStudyTime = projectCourseInfo.getTotal_studytime() + Long.valueOf(study_time);
               Long trainStudyTime=projectCourseInfo.getTrain_studytime() + Long.valueOf(study_time);
               String finishStatus = "-1";
               if(totalStudyTime >= requirementStudytime*60){
                   finishStatus = "1";
               }
               pcInfo.setRequirement_studytime(requirementStudytime);
               pcInfo.setTotal_studytime(totalStudyTime);
               if(projectCourseInfo.getTrain_studytime()==null || projectCourseInfo.getTrain_studytime()==0L){
                   pcInfo.setTrain_studytime(Long.valueOf(study_time));
               }else{
                   pcInfo.setTrain_studytime(trainStudyTime);
               }
               pcInfo.setFinish_status(finishStatus);
               pcInfo.setCreate_user(user_name);
               pcInfo.setOper_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
               projectCourseInfoMapper.update(pcInfo);
           }

           // 项目个人学时统计 project_statistics_info
           ProjectStatisticsInfo psi=new ProjectStatisticsInfo();
           psi.setProject_id(svp.getProject_id());
           psi.setUser_id(svp.getUser_id());
           ProjectStatisticsInfo projectStatisticsInfo = projectStatisticsInfoMapper.selectOne(psi);
           if(null != projectStatisticsInfo) {
               //判断应修学时和已修学时，来更新train_status培训状态（1未完成2完成）
               Long requirementStudytime = projectStatisticsInfo.getRequirement_studytime();
               Long totalStudyTime = projectStatisticsInfo.getTotal_studytime() + Long.valueOf(study_time);
               String trainStatus = "1";
               if(totalStudyTime >= requirementStudytime*60){
                   trainStatus = "2";
               }
               projectStatisticsInfo.setId(projectStatisticsInfo.getId());
               projectStatisticsInfo.setRequirement_studytime(requirementStudytime);
               projectStatisticsInfo.setTotal_studytime(totalStudyTime);
               projectStatisticsInfo.setTrain_studytime(projectStatisticsInfo.getTrain_studytime() + Long.valueOf(study_time));
               projectStatisticsInfo.setTrain_status(trainStatus);
               projectStatisticsInfo.setUser_name(user_name);
               projectStatisticsInfo.setOper_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
               projectStatisticsInfoMapper.update(projectStatisticsInfo);
           }

           // 个人档案信息 person_dossier_info
           PersonDossier pdm=new PersonDossier();
           pdm.setUser_id(svp.getUser_id());
           PersonDossier  personDossier = personDossierMapper.selectOne(pdm);
           pdm.setCompany_id(personDossier.getCompany_id());
           if(null != personDossier) {
               pdm.setId(personDossier.getId());
               pdm.setYear_studytime(personDossier.getYear_studytime()+Long.valueOf(study_time));
               pdm.setTotal_studytime(personDossier.getTotal_studytime()+Long.valueOf(study_time));

               //判断是否是第一次添加学时
               if(projectStatisticsInfo.getTotal_studytime() .equals(study_time) ){
                   pdm.setTrain_count(personDossier.getTrain_count()+1);
               }
               pdm.setOper_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
               personDossierMapper.update(pdm);
           }
       }

        return result;
    }
}
