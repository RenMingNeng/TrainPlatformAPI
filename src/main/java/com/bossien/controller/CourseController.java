package com.bossien.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.bossien.common.anno.TokenSecurity;
import com.bossien.common.bean.Response;
import com.bossien.common.util.MapUtil;
import com.bossien.common.util.PropertiesUtils;
import com.bossien.common.util.StringUtil;
import com.bossien.entity.Attachment;
import com.bossien.entity.Course;
import com.bossien.entity.request.AdminProjectInfo;
import com.bossien.entity.request.StudentViewCourseJson;
import com.bossien.entity.request.AttachmentList;
import com.bossien.entity.request.CourseInfoJson;
import com.bossien.entity.request.PerfectExercise;
import com.bossien.entity.request.QuestionTypeList;
import com.bossien.service.*;
import com.bossien.service.ICourseInfoService;
import com.bossien.service.ICourseService;
import com.bossien.service.IProjectCourseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 课程控制器
 * @author renmingneng
 * @date 2017年10月18日
 */
@RestController
@RequestMapping("/api/v1.0/course")
@Api(value = "课程")
public class CourseController {

    @Autowired private ICourseInfoService courseInfoService;
    @Autowired private IProjectCourseInfoService projectCourseInfoService;
    @Autowired
     private ICourseService courseService;
    @Autowired
    private ICourseAttachmentService courseAttachmentService;
    @Autowired
    private IAttachmentService attachmentService;

    /**
     * 学员获取项目下的课程集合
     */
    @ApiOperation(
            value = "学员获取项目下的课程集合",response = Response.class, produces = "application/json",
            notes = "1、page_index 必填<br>2、page_size 必填<br>3、project_id 必填<br>4、user_id 必填")
    @TokenSecurity
    @RequestMapping(value = "/selectCourseByProject", method= RequestMethod.POST)
    public Response selectCourseByProject(@RequestBody StudentViewCourseJson api)
    {
        if(StringUtil.trimAndIsEmpty(api.getProject_id())){
            return new Response().failure("单位编号不能为空");
        }
        if(StringUtil.trimAndIsEmpty(api.getUser_id())){
            return new Response().failure("人员编号不能为空");
        }

        Page<Map<String, Object>> page = new Page<Map<String, Object>>();
        Map map = MapUtil.getInstance();
        map.put("project_id",api.getProject_id());
        map.put("user_id",api.getUser_id());
        // 数量
        Integer count = projectCourseInfoService.selectProjectCourseCount(map);
        map.put("start_num", (api.getPage_index() - 1) * api.getPage_size());
        map.put("end_num", api.getPage_size());
        List<Map<String, Object>> listMap = projectCourseInfoService.selectProjectCourseList(map);
        page.setRecords(listMap);
        page.setSize(api.getPage_size());
        page.setCurrent(api.getPage_index());
        page.setTotal(count);
        return new Response().success(page);
    }

    /**
     * 课程详情接口
     * @param courseInfoJson
     * @return
     */
    @TokenSecurity
    @ApiOperation(value = "查询该课程的课程名称课程详情等信息",response = Response.class, produces = "application/json")
    @RequestMapping(value = "/desc", method= RequestMethod.POST)
    public Response selectCourseInfo(@RequestBody CourseInfoJson courseInfoJson){

        if("".equals(courseInfoJson.getCourse_id()) || courseInfoJson.getCourse_id() == null){
            return new Response().failure("请求参数不能为空");
        }

        //根据courseId查询课程信息
        Course  course = courseService.selectOne(courseInfoJson.getCourse_id());

        if(null == course) {
            return new Response().failure("课程不存在");
        }

        Map resp = MapUtil.getInstance();
        MapUtil.put(resp, "course_name", course.getVarName());
        MapUtil.put(resp, "course_desc", course.getVarDesc());
        return new Response().success(resp);
    }


    /**
     * 查询该课程下视频的集合
     * @param attachmentList
     * @return
     */
    @TokenSecurity
    @ApiOperation(value = "查询该课程下视频的集合",response = Response.class, produces = "application/json")
    @RequestMapping(value = "/video/page", method= RequestMethod.POST)
    public Response selectFileByCourse(
            @RequestBody AttachmentList attachmentList
            ){
        String course_id = attachmentList.getCourse_id();
        String user_id = attachmentList.getUser_id();

        if("".equals(course_id) || course_id == null){
            return new Response().failure("课程Id不能为空");
        }
        if("".equals(user_id) || user_id == null){
            return new Response().failure("账号Id不能为空");
        }
        Map map = MapUtil.getInstance();
        map.put("course_id",course_id);
        map.put("user_id",user_id);

        Course course = null ;
        //根据courseId查询课程信息
        course = courseService.selectOne(course_id);
        if(null == course) {
            return new Response().failure("课程不存在");
        }
        map.put("course_name",course.getVarName());
        map.put("course_desc",course.getVarDesc());

        //根据courseId查询附件id集合
        List<String> attachmentIds = courseAttachmentService.selectAttachmentIds(map);
        if(attachmentIds.size() <=0){
            return new Response().failure("暂无数据");
        }
        map.put("intIds",attachmentIds);


        //根据attachmentIds查询video集合
       Map<String,Object>  attachments = courseService.assemblyCourseAndAttachment(map);

        return new Response().success(attachments);
    }


    /**
     * 课程列表
     * @return
     */
    @TokenSecurity
    @ApiOperation(value = "课程列表",response = Response.class, produces = "application/json")
    @RequestMapping(value = "/page", method= RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),})
    public Response page(@RequestBody PerfectExercise perfectExercise){
        if(StringUtils.isEmpty(perfectExercise.getProject_id())){
            return new Response().failure("项目编号不能为空");
        }
        if(StringUtils.isEmpty(perfectExercise.getUser_id())){
            return new Response().failure("用户编号不能为空");
        }
        Map<String, Object> map = MapUtil.getInstance();
        MapUtil.put(map,"project_id",perfectExercise.getProject_id());
        MapUtil.put(map,"user_id",perfectExercise.getUser_id());
        List<Map<String, Object>> result = projectCourseInfoService.selectCourseList(map);
        if(result == null || result.size()<1){
            return new Response().failure("暂无数据");
        }
        return new Response().success(result);
    }

}
