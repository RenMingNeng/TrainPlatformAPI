package com.bossien.controller;

import com.bossien.common.anno.TokenSecurity;
import com.bossien.common.bean.Response;
import com.bossien.entity.request.LatelyStudyCourse;
import com.bossien.entity.request.StudyCourseResponse;
import com.bossien.service.ICourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1.0/course")
@Api(value = "学员首页最近学习和最新上传课程列表接口")
public class LatelyStudyController {
	
	@Autowired
	private ICourseService courseService;

	@TokenSecurity
	@ApiOperation(value = "查询最近学习和最近上传的所有课程集合",response = Response.class)
	@RequestMapping(value = "/studyCourseList",method=RequestMethod.POST)
	//@ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),})
	public Response latelyStudyCourseList(@RequestBody LatelyStudyCourse lsc){
		StudyCourseResponse scr=new StudyCourseResponse();
		int scount=0;
		if(StringUtils.isEmpty(lsc.getCompany_id())){
			return new Response().failure("公司id不能为空");
		}
		if(StringUtils.isEmpty(lsc.getUser_id())){
			return new Response().failure("用户id不能为空");
		}
		//最近学习的课程列表
		List<Map<Object, Object>> studylist = courseService.queryLatelyStudyCourseList(lsc);
		//最近上传的课程列表
		List<Map<String, Object>> uploadlist=courseService.queryLatelyUploadCourseList(lsc);
		scount=courseService.selectLatelyStudyCount(lsc);
		if(studylist!=null || uploadlist!=null){
			scr.setStudylist(studylist);
			scr.setUploadlist(uploadlist);
			scr.setStudylistCount(scount);
		}else{
			return new Response().failure("暂无数据");
		}
		return new Response().success(scr);

	}
	
}
