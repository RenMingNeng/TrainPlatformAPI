package com.bossien.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.entity.CourseInfo;
import com.bossien.mapper.ap.CompanyCourseMapper;
import com.bossien.mapper.tp.CourseInfoMapper;
import com.bossien.service.ICompanyCourseService;
import com.bossien.service.ICourseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/18.
 */
@Service
public class CourseInfoServiceImpl extends ServiceImpl<CourseInfoMapper, CourseInfo> implements ICourseInfoService{
    @Autowired private CourseInfoMapper courseInfoMapper;
    @Autowired private ICompanyCourseService companyCourseService;
    /**
     * 课程下的总题量
     *
     * @param params
     * @return
     */
    @Override
    public int selectCourseQuestionCount(Map<String, Object> params) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("intCompanyId", params.get("company_id").toString());
        //根据companyId查询courseIds
        List<Map<String, Object>> companyCourses = companyCourseService.selectByCompanyId(param);
        List<String> courseIds = new ArrayList<String>();
        if (null != companyCourses && companyCourses.size() > 0) {
            for (Map map : companyCourses) {
                courseIds.add(map.get("intCourseId").toString());
            }
        }
        if (courseIds.size() == 0) {
            return 0;
        }
        param.put("courseIds", courseIds);
        return courseInfoMapper.selectCourseQuestionCount(param);
    }
}
