package com.bossien.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.bossien.common.anno.TokenSecurity;
import com.bossien.common.bean.Response;
import com.bossien.entity.enumeration.ProjectStatusEnum;
import com.bossien.entity.request.AdminProjectList;
import com.bossien.service.IProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/v1.0/project")
@Api(value = "管理员项目列表接口")
public class AdminProjectListController {
	
	@Autowired
	private IProjectService projectService;

	@TokenSecurity
	@ApiOperation(value = "分页查询单位下所有项目列表",response = Response.class)
	@RequestMapping(value = "/projectListPage",method=RequestMethod.POST)
	public Response projectlist(@RequestBody AdminProjectList apj){
		if("".equals(apj.getCompany_id()) || apj.getCompany_id()==null){
			return new Response().failure("请求参数公司编号为空");
		}
		if(apj.getProject_status()==null){
			apj.setProject_status(ProjectStatusEnum.ProjectStatus_3.getValue());//默认查询进行中的项目
		}
		Page<Map<Object, Object>> pageData = projectService.selectProjectListPage(new Page<Map<Object, Object>>(apj.getPage_index(), apj.getPage_size()),apj);
		if(pageData.getRecords().size()==0 || pageData.getRecords().isEmpty()){
			return new Response().failure("暂无数据");
		}
		return new Response().success(pageData);

	}
	
}
