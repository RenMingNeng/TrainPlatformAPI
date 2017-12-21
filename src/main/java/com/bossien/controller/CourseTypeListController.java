package com.bossien.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.bossien.common.anno.TokenSecurity;
import com.bossien.common.bean.Response;
import com.bossien.entity.enumeration.CourseTypeEnum;
import com.bossien.entity.request.CourseTypeList;
import com.bossien.entity.request.LatelyStudyCourse;
import com.bossien.service.ICourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1.0/course")
@Api(value = "学员首页分类下的课程列表接口")
public class CourseTypeListController {
	
	@Autowired
	private ICourseService courseService;

	@TokenSecurity
	@ApiOperation(value = "查询学员首页课程分类下的所有课程集合",response = Response.class)
	@RequestMapping(value = "/courseTypeList",method=RequestMethod.POST)
	//@ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),})
	public Response courseList(@RequestBody CourseTypeList ctl){
        Map<String,Object> param=new HashMap<String,Object>();
		if(StringUtils.isEmpty(ctl.getCompany_id())){
			return new Response().failure("公司id不能为空");
		}
		if(StringUtils.isEmpty(ctl.getCourse_type())){
			return new Response().failure("课程分类不能为空");
		}

		//首页最近学习点击更多查询
		if(ctl.getCourse_type().equals(CourseTypeEnum.MORE.getValue())){
			if(StringUtils.isEmpty(ctl.getUser_id())){
				return new Response().failure("用户id不能为空");
			}

			param.put("user_id",ctl.getUser_id());
			param.put("company_id",ctl.getCompany_id());
			param.put("course_type",ctl.getCourse_type());
			//最近学习的课程列表
			Page<Map<Object, Object>> studylist = courseService.queryLatelyStudyCourseList(new Page<Map<Object, Object>>(ctl.getPage_index(), ctl.getPage_size()), param);
			if(studylist==null || studylist.getSize()==0){
				return new Response().failure("暂无数据");
			}
			return new Response().success(studylist);
		}
		//模糊查询
		if(ctl.getCourse_type().equals(CourseTypeEnum.SEARCH.getValue())){
			if(StringUtils.isEmpty(ctl.getCourse_name())){
				return new Response().failure("请输入搜索条件查询");
			}
			Page<Map<Object, Object>> calist = courseService.searchAllCourseList(new Page<Map<Object, Object>>(ctl.getPage_index(), ctl.getPage_size()), ctl);
			if(calist==null || calist.getSize()==0){
				return new Response().failure("暂无数据");
			}
			return new Response().success(calist);
		}

		Page<Map<Object, Object>> clist = courseService.queryCourseListByType(new Page<Map<Object, Object>>(ctl.getPage_index(), ctl.getPage_size()), ctl);
		if(clist==null || clist.getTotal()==0){
			return new Response().failure("暂无数据");
		}
		return new Response().success(clist);

	}
	
}
