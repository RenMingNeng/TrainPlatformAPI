package com.bossien.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.MapUtils;
import com.bossien.common.anno.TokenSecurity;
import com.bossien.common.bean.Response;
import com.bossien.common.util.MapUtil;
import com.bossien.entity.ProjectExerciseOrder;
import com.bossien.entity.request.UserRankList;
import com.bossien.service.IProjectExerciseOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 任务排行控制器
 * @date 2017年10月17日 上午11:26:00
 */
@RestController
@RequestMapping("/api/v1.0/score")
@Api(value = "排行")
public class ScoreController {
	@Autowired
	private IProjectExerciseOrderService projectExerciseOrderService;
	/**
	 * 任务排行榜
	 */
	@TokenSecurity
	@ApiOperation(value = "任务排行榜",response = Response.class, produces = "application/json")
    @RequestMapping(value = "/rank", method=RequestMethod.POST)
	@ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),})
    public  Response rank(@RequestBody UserRankList userRankList){
		if(StringUtils.isEmpty(userRankList.getProject_id())){
			return new Response().failure("项目编号不能为空");
		}
		Map params = MapUtil.getInstance();
		MapUtil.put(params,"project_id",userRankList.getProject_id());
		List<Map<String,Object>> result = projectExerciseOrderService.selectUserRank(new Page<Map<Object, Object>>(userRankList.getPage_index(),userRankList.getPage_size()),params);
		if(result == null || result.size()<1){
			return new Response().failure("暂无数据");
		}
		Integer count = projectExerciseOrderService.selectUserRankCount(params);
		params.clear();
		MapUtil.put(params,"datas",result);
		MapUtil.put(params,"count",count);
		return new Response().success(params);

    }


}
