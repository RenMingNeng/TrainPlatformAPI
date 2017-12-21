package com.bossien.controller;

import com.bossien.common.anno.TokenSecurity;
import com.bossien.common.bean.Response;
import com.bossien.entity.ProjectExerciseOrder;
import com.bossien.entity.request.PerfectExercise;
import com.bossien.service.IProjectExerciseOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 练习记录控制器
 * @date 2017年10月17日 上午11:26:00
 */
@RestController
@RequestMapping("/api/v1.0/exercise")
@Api(value = "练习")
public class ProjectExerciseOrderController {
	@Autowired
	private IProjectExerciseOrderService projectExerciseOrderService;
	/**
	 * 查看练习答题统计
	 */
	@TokenSecurity
	@ApiOperation(value = "练习答题统计",response = Response.class, produces = "application/json")
    @RequestMapping(value = "/analyze", method=RequestMethod.POST)
	@ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),})
    public  Response analyze(@RequestBody PerfectExercise perfectExercise){
		if(StringUtils.isEmpty(perfectExercise.getProject_id())){
			return new Response().failure("项目编号不能为空");
		}
		if(StringUtils.isEmpty(perfectExercise.getUser_id())){
			return new Response().failure("用户编号不能为空");
		}
		ProjectExerciseOrder projectExerciseOrder = projectExerciseOrderService.selectOne(new ProjectExerciseOrder(perfectExercise.getProject_id(),perfectExercise.getUser_id()));
		if(null == projectExerciseOrder){
			return new Response().failure("暂无数据");
		}
		return new Response().success(projectExerciseOrder);

    }


}
