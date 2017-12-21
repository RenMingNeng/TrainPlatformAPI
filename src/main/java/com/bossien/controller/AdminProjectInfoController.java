package com.bossien.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.bossien.common.anno.TokenSecurity;
import com.bossien.common.bean.Response;
import com.bossien.entity.request.AdminProjectInfo;
import com.bossien.service.IProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1.0/project")
@Api(value = "管理员查看项目详情接口")
public class AdminProjectInfoController {
	
	@Autowired
	private IProjectService projectService;

	@TokenSecurity
	@ApiOperation(value = "分页查询培训 练习 考试详情",response = Response.class)
	@RequestMapping(value = "/everyProjectInfo",method=RequestMethod.POST)
	public Response projectInfo(@RequestBody AdminProjectInfo api){
		Page<Map<Object, Object>> pageData=null;
		Map<String, Object> cmap=new HashMap<String, Object>();
		if(StringUtils.isEmpty(api.getShow_type())){
			return new Response().failure("请求参数显示类型为空");
		}
		if(StringUtils.isEmpty(api.getUser_id())){
			return new Response().failure("请求参数学员id为空");
		}
		if(StringUtils.isEmpty(api.getProject_id())){
			return new Response().failure("请求参数为空");
		}
		if("1".equals(api.getShow_type())){//培训详情
			pageData=projectService.queryTrainProInfo(new Page<Map<Object, Object>>(api.getPage_index(), api.getPage_size()),api);
		}else if("2".equals(api.getShow_type())){//练习详情
			pageData=projectService.queryExerciseProInfo(new Page<Map<Object, Object>>(api.getPage_index(), api.getPage_size()),api);
		}else if("3".equals(api.getShow_type())){//考试详情
			cmap = projectService.queryExamProInfo(api);
			if(cmap==null || cmap.isEmpty()){
				return new Response().failure("暂无数据");
			}
			return new Response().success(cmap);
		}else{
			return new Response().failure("暂无数据");
		}
		if(pageData.getRecords().size()==0 || pageData.getRecords().isEmpty()){
			return new Response().failure("暂无数据");
		}
		return new Response().success(pageData);

	}
	
}
