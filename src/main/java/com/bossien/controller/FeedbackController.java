package com.bossien.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.bossien.common.anno.TokenSecurity;
import com.bossien.common.bean.Response;
import com.bossien.common.util.ValidateUtil;
import com.bossien.entity.Feedback;
import com.bossien.service.IFeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * 反馈控制器
 * @author chengcheng.luo
 * @date 2017年10月17日 上午11:26:00
 */
@RestController
@RequestMapping("/api/v1.0/feedback")
@Api(value = "反馈")
public class FeedbackController {
	
	@Autowired private IFeedbackService feedbackService;
	/**
	 * 分页查询反馈
	 */
	@ApiOperation(value = "分页查询反馈",response = Response.class, notes = "备注信息写这里")
    @RequestMapping(value = "/list",method=RequestMethod.GET)  
    public  Response list(@RequestParam (required = true) Integer pageIndex,@RequestParam (defaultValue="10")Integer size){
		
		Page<Feedback> pageData = feedbackService.selectPage(new Page<Feedback>(pageIndex, size));
		return new Response().success(pageData);
		
    }

	/**
	 * 获取一条反馈数据
	 */
	@ApiOperation(value = "获取一条反馈数据",response = Response.class)
    @RequestMapping(value = "/get/{id}",method=RequestMethod.GET)
    public  Response get(@PathVariable("id") String id){
		return new Response().success(feedbackService.selectById(id));

    }

	/**
	 * 删除反馈
	 */
	@TokenSecurity // 表示需要登录进行Token校验
	@ApiOperation(value = "删除反馈",response = Response.class)
    @RequestMapping(value = "/delete/{id}",method=RequestMethod.DELETE)
    public  Response delete(@PathVariable("id") String id){
		feedbackService.deleteById(id);
		return new Response().success();
    }
	
}
