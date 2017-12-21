package com.bossien.service.impl;

import com.bossien.common.util.MapUtil;
import com.bossien.entity.CourseQuestion;
import com.bossien.entity.ProjectCourse;
import com.bossien.entity.ProjectUser;
import com.bossien.entity.Question;
import com.bossien.mapper.ap.CourseQuestionMapper;
import com.bossien.mapper.ap.QuestionMapper;
import com.bossien.mapper.tp.ProjectCourseMapper;
import com.bossien.mapper.tp.ProjectUserMapper;
import com.bossien.service.ICourseQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by huangzhaoyong on 2017/7/25.
 */

@Service
public class CourseQuestionServiceImpl implements ICourseQuestionService {

    @Autowired
    private CourseQuestionMapper courseQuestionMapper;
    @Autowired
    private ProjectUserMapper projectUserMapper;
    @Autowired
    private ProjectCourseMapper projectCourseMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public Map<String, String> selectCourseIdByQuestionId(String project_id, String user_id) {
        //先查询角色 项目id+用户id
        ProjectUser projectUser = projectUserMapper.selectByProjectIdAndUserId(project_id, user_id);
        if (null == projectUser) {
            return MapUtil.getInstance();
        }

        //再根据角色查询课程集合
        List<String> courseList = projectCourseMapper.selectCourseIds(new ProjectCourse(project_id, projectUser.getRole_id()));
        //根据课程集合查询所有题
        Map<String, Object> params = MapUtil.getInstance();
        params.put("intCourseIds", courseList);
        List<CourseQuestion> courseQuestionList = courseQuestionMapper.selectList(params);
        return listToMap(courseQuestionList);
    }

    /**
     * list转换成map<question_id,course_id>
     *
     * @param courseQuestionList
     */
    public Map<String, String> listToMap(List<CourseQuestion> courseQuestionList) {
        Map<String, String> map = MapUtil.getInstance();
        for (CourseQuestion courseQuestion : courseQuestionList) {
            map.put(courseQuestion.getIntQuestionId(), courseQuestion.getIntCourseId());
        }
        return map;
    }

    public List<Question> selectQuestionByProjectAndUserId(String project_id, String user_id) {
        //先查询角色 项目id+用户id
        ProjectUser projectUser = projectUserMapper.selectByProjectIdAndUserId(project_id, user_id);
        if (null == projectUser) {
            return new ArrayList<Question>();
        }

        //再根据角色查询课程集合
        List<String> courseList = projectCourseMapper.selectCourseIds(new ProjectCourse(project_id, projectUser.getRole_id()));
        //根据课程集合查询所有题
        Map<String, Object> params = MapUtil.getInstance();
        params.put("intCourseIds", courseList);
        List<CourseQuestion> courseQuestionList = courseQuestionMapper.selectList(params);
        List<String> questionList = new ArrayList<String>();
        for (CourseQuestion course : courseQuestionList) {
            questionList.add(course.getIntQuestionId());
        }
        params.put("intIds", questionList);
        params.put("chrValid", "1");
        return questionMapper.selectList(params);
    }
}
