package com.bossien.controller;

import com.bossien.common.bean.Response;
import com.bossien.common.util.MapUtil;
import com.bossien.entity.CompanyTj;
import com.bossien.service.ICompanyService;
import com.bossien.service.ICompanyTjService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 单位控制器
 * @author chengcheng.luo
 * @date 2017年10月17日 上午11:26:00
 */
@RestController
@RequestMapping("/api/v1.0/company")
@Api(value = "单位")
public class CompanyController {
	
	@Autowired private ICompanyService companyService;
    @Autowired private ICompanyTjService companyTjService;
	/**
	 * 获取一条单位数据
	 */
	@ApiOperation(value = "获取一条单位数据",response = Response.class, produces = "application/json")
	@ApiImplicitParams(value = {@ApiImplicitParam(name = "id", value = "单位id", required = true, paramType = "query", dataType = "String")})
    @RequestMapping(value = "/get", method=RequestMethod.GET)
    public  Response get(@RequestParam(value = "id", required = true) String id){

		return new Response().success(companyService.selectByIntId(id));

    }



}
