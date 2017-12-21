package com.bossien.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.common.util.FastDFSUtils;
import com.bossien.common.util.JsonUtil;
import com.bossien.common.util.MapUtil;
import com.bossien.common.util.StringUtil;
import com.bossien.entity.Course;
import com.bossien.entity.ProjectCourseInfo;
import com.bossien.mapper.ap.CourseMapper;
import com.bossien.mapper.tp.LatelyStudyMapper;
import com.bossien.mapper.tp.ProjectCourseInfoMapper;
import com.bossien.service.IProjectCourseInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/18.
 */
@Service
public class ProjectCourseInfoServiceImpl extends ServiceImpl<ProjectCourseInfoMapper, ProjectCourseInfo> implements IProjectCourseInfoService {

    @Autowired
    private ProjectCourseInfoMapper projectCourseInfoMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private LatelyStudyMapper latelyStudyMapper;

    public List<Map<String, Object>> selectProjectCourseList(Map map) {
        List<Map<String, Object>> listMap = projectCourseInfoMapper.selectList(map);
        String courseId = "";
        String user_id = map.get("user_id").toString();
        for (Map<String, Object> _map : listMap) {
            courseId = _map.get("course_id").toString();
            Map map1 = MapUtil.getInstance();
            map1.put("course_id",courseId);
            _map.put("use_num",latelyStudyMapper.queryStudyCount(map1));          // 使用人数
            _map.put("cover_url",this.getCourseCover(courseId));                   // 课程封面
        }
        return listMap;
    }


    public Map<String, Object> selectCourseInfo(Map<String, Object> map) {
        return projectCourseInfoMapper.selectCourseInfo(map);
    }

    public ProjectCourseInfo selectOne(ProjectCourseInfo projectCourseInfo) {

        return projectCourseInfoMapper.selectOne(projectCourseInfo);
    }

    public void update(ProjectCourseInfo projectCourseInfo) {

        projectCourseInfoMapper.update(projectCourseInfo);
    }

    public List<Map<String, Object>> selectCourseList(Map<String, Object> map) {
        return projectCourseInfoMapper.selectCourseList(map);
    }

    public List<String> selectCourseIds(Map<String, Object> map) {
        return projectCourseInfoMapper.selectCourseIds(map);
    }

    public Integer selectProjectCourseCount(Map map) {

        return projectCourseInfoMapper.selectCount(map);
    }

    // 到基础数据库获取课程封面
    private String getCourseCover(String courseId) {
        Course course = courseMapper.selectOne(courseId);
        String url = "";
        if(null == course){ return url;}
        String varCoverInfo = course.getVarCoverInfo();
        if(StringUtils.isNotEmpty(varCoverInfo)){

            //循环外层数据得到map
            Map<String, Object> resultMap = JsonUtil.jsonToMap(course.getVarCoverInfo());
            if(resultMap!=null && resultMap.get("images")!=null){
                //继续循环内层json数组
                List<Map<String, Object>> dataListMap = JsonUtil.jsonArrayToMap(resultMap.get("images"));
                if(dataListMap.size()>0 && !dataListMap.isEmpty()){
                    for(Map<String, Object> dataMap:dataListMap){
                        if(dataMap.get("fileId")!=null){
                            url=FastDFSUtils.getURLToken(dataMap.get("fileId").toString());
                        }
                    }
                }
            }
        }
        return url;
    }

}
