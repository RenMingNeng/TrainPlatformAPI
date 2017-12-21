package com.bossien.controller;

import com.bossien.common.anno.TokenSecurity;
import com.bossien.common.bean.Response;
import com.bossien.entity.request.SaveVideoPosition;
import com.bossien.service.IVideoPositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/video")
@Api(value = "保存视频播放位置接口")
public class VideoPositionController {
	
	@Autowired
	private IVideoPositionService videoPositionService;

	@TokenSecurity
	@ApiOperation(value = "保存用户视频播放信息",response = Response.class)
	@RequestMapping(value = "/saveUserVideoInfo",method=RequestMethod.POST)
	@ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),})
	public  Response saveUserVideoInfo(@RequestBody SaveVideoPosition svp){

		if(StringUtils.isEmpty(svp.getUser_id())){
			return new Response().failure("用户Id不能为空");
		}
		if(StringUtils.isEmpty(svp.getCourse_id())){
			return new Response().failure("课程Id不能为空");
		}
		if(StringUtils.isEmpty(String.valueOf(svp.getStudy_time()))){
			return new Response().failure("本次学习时长不能为空");
		}
		int result = videoPositionService.saveUserVideoInfo(svp);
		if(result>0) {
			return new Response().success();
		} else {
			return new Response().failure("保存用户视频播放信息失败");
		}

	}
	
}
