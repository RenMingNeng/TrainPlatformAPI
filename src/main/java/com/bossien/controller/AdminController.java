package com.bossien.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.bossien.common.anno.TokenSecurity;
import com.bossien.common.bean.Response;
import com.bossien.common.util.MapUtil;
import com.bossien.common.util.ParamsUtil;
import com.bossien.common.util.StringUtil;
import com.bossien.entity.CompanyTj;
import com.bossien.entity.request.*;
import com.bossien.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1.0/admin")
@Api(value = "管理员")
public class AdminController {

    @Autowired
    private IProjectUserService projectUserService;
    @Autowired
    private IPersonDossierService personDossierService;
    @Autowired
    private IExamScoreService examScoreService;
    @Autowired
    private IProjectDepartmentService projectDepartmentService;
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private ICompanyTjService companyTjService;
    @Autowired
    private IProjectCourseService projectCourseService;
    @Autowired
    private IProjectInfoService projectInfoService;


    @ApiOperation(
            value = "管理员查看项目基本信息",response = Response.class, produces = "application/json",
            notes = "1、project_id 必填")
    @TokenSecurity
    @RequestMapping(value = "/viewProjectInfo",  method=RequestMethod.POST)
    public Response viewProjectInfo(@RequestBody AdminViewProjectJson api)
    {
        String project_id = api.getProject_id();
        if(StringUtil.trimAndIsEmpty(project_id)){
            return new Response().failure( "项目编号不能为空");
        }
        // 结果集
        Map map = projectInfoService.selectProjectById(project_id);
        if(null == map || map.size() == 0){
            return new Response().failure("暂无数据");
        }
        return new Response().success(map);
    }


    @ApiOperation(
            value = "管理员查看项目课程列表信息",response = Response.class, produces = "application/json",
            notes = "1、page_index 必填<br>2、page_size 必填<br>3、project_id 必填")
    @TokenSecurity
    @RequestMapping(value = "/viewCourseList", method= RequestMethod.POST)
    public Response viewCourseList(@RequestBody AdminViewCourseJson api)
    {
        String project_id = api.getProject_id();
        Integer page_index = api.getPage_index();
        Integer page_size = api.getPage_size();
        if(StringUtil.trimAndIsEmpty(project_id)){
            return new Response().failure( "项目编号不能为空");
        }
        if(null == page_index){
            return new Response().failure( "page_index不能为空");
        }
        if(null == page_size){
            return new Response().failure( "page_size不能为空");
        }
        Page<Map<String, Object>> page = new Page<Map<String, Object>>();
        Map map = MapUtil.getInstance();
        map.put("project_id",project_id);
        // 数量
        Integer count = projectCourseService.selectCountByProjectId(project_id);
        map.put("start_num", (page_index - 1) * page_size);
        map.put("end_num", page_size);
        // 集合
        List<Map<String, Object>> listMap = projectCourseService.selectListByParams(map);
        page.setRecords(listMap);
        page.setSize(api.getPage_size());
        page.setCurrent(page_index);
        page.setTotal(count);
        if(null == listMap || listMap.size() == 0){
            return new Response().failure("暂无数据");
        }
        return new Response().success(page);
    }

    @ApiOperation(
            value = "管理员查看含培训项目下单位的学习进度信息",response = Response.class, produces = "application/json",
            notes = "1、page_index 必填<br>2、page_size 必填<br>3、company_id 必填<br>4、project_id 必填")
    @TokenSecurity
    @RequestMapping(value = "/studyProgress", method= RequestMethod.POST)
    public Response studyProgress(@RequestBody AdminViewProgressJson api)
    {
        String company_id = api.getCompany_id();
        String project_id = api.getProject_id();
        String dept_name = api.getDept_name();
        Integer page_index = api.getPage_index();
        Integer page_size = api.getPage_size();
        if(StringUtil.trimAndIsEmpty(project_id)){
            return new Response().failure( "项目编号不能为空");
        }
        if(StringUtil.trimAndIsEmpty(company_id)){
            return new Response().failure( "单位编号不能为空");
        }
        if(null == page_index){
            return new Response().failure( "page_index不能为空");
        }
        if(null == page_size){
            return new Response().failure( "page_size不能为空");
        }
        Page<Map<String, Object>> page = new Page<Map<String, Object>>();
        Map map = MapUtil.getInstance();
        map.put("company_id",company_id);
        map.put("project_id",project_id);
        map.put("dept_name",ParamsUtil.joinLike(dept_name));
        // 数量
        Integer count = projectDepartmentService.studyProgressCount(map);
        map.put("start_num", (page_index - 1) * page_size);
        map.put("end_num", page_size);
        // 集合
        List<Map<String, Object>> listMap = projectDepartmentService.studyProgressList(map);
        if(null == listMap || listMap.size() == 0){
            return new Response().failure("暂无数据");
        }
        page.setRecords(listMap);
        page.setSize(page_size);
        page.setCurrent(page_index);
        page.setTotal(count);
        return new Response().success(page);
    }

    @ApiOperation(
            value = "管理员查看项目下单位的考试合格率信息",response = Response.class, produces = "application/json",
            notes = "1、page_index 必填<br>2、page_size 必填<br>3、company_id 必填<br>4、project_id 必填")
    @TokenSecurity
    @RequestMapping(value = "/userPassScore", method= RequestMethod.POST)
    public Response userPassScore(@RequestBody AdminViewPassJson api)
    {
        String company_id = api.getCompany_id();
        String project_id = api.getProject_id();
        String dept_name = api.getDept_name();
        Integer page_index = api.getPage_index();
        Integer page_size = api.getPage_size();
        if(StringUtil.trimAndIsEmpty(company_id)){
            return new Response().failure( "单位编号不能为空");
        }
        if(StringUtil.trimAndIsEmpty(project_id)){
            return new Response().failure("项目编号不能为空");
        }
        if(null == page_index){
            return new Response().failure( "page_index不能为空");
        }
        if(null == page_size){
            return new Response().failure( "page_size不能为空");
        }
        Page<Map<String, Object>> page = new Page<Map<String, Object>>();
        Map map = MapUtil.getInstance();
        map.put("company_id",company_id);
        map.put("project_id",project_id);
        map.put("dept_name",ParamsUtil.joinLike(dept_name));
        // 数量
        Integer count = projectDepartmentService.selectCountByParams(map);
        map.put("start_num", (page_index - 1) * page_size);
        map.put("end_num", page_size);
        // 集合
        List<Map<String, Object>> listMap = projectDepartmentService.selectListByParams(map);
        if(null == listMap || listMap.size() == 0){
            return new Response().failure("暂无数据");
        }
        page.setRecords(listMap);
        page.setSize(page_size);
        page.setCurrent(page_index);
        page.setTotal(count);
        return new Response().success(page);
    }

    @ApiOperation(
            value = "管理员查看项目下单位的考试成绩列表信息",response = Response.class, produces = "application/json",
            notes = "1、page_index 必填<br>2、page_size 必填<br>3、company_id 必填<br>4、project_id 必填")
    @TokenSecurity
    @RequestMapping(value = "/userScoreList", method= RequestMethod.POST)
    public Response userScoreList(@RequestBody AdminViewScoreJson api)
    {
        String company_id = api.getCompany_id();
        String project_id = api.getProject_id();
        String user_name = api.getUser_name();
        Integer page_index = api.getPage_index();
        Integer page_size = api.getPage_size();
        if(StringUtil.trimAndIsEmpty(company_id)){
            return new Response().failure( "单位编号不能为空");
        }
        if(StringUtil.trimAndIsEmpty(project_id)){
            return new Response().failure( "项目编号不能为空");
        }
        if(null == page_index){
            return new Response().failure( "page_index不能为空");
        }
        if(null == page_size){
            return new Response().failure( "page_size不能为空");
        }
        Page<Map<String, Object>> page = new Page<Map<String, Object>>();
        Map map = MapUtil.getInstance();
        map.put("project_id",project_id);
        map.put("user_name",ParamsUtil.joinLike(user_name));
        map.put("start_num", (page_index - 1) * page_size);
        map.put("end_num", page_size);
        // 项目下学员id集合
        List<String> user_ids = projectUserService.selectUserIdsByParam(map);
        if(null == user_ids || user_ids.size() == 0){
            return new Response().failure("暂无数据");
        }
        map.put("exam_type","2");
        map.put("company_id",company_id);
        map.put("user_ids",user_ids);
        // 数量
        Integer count = examScoreService.userScoreCount(map);

        // 集合
        List<Map<String, Object>> listMap = examScoreService.userScoreList(map);
        page.setRecords(listMap);
        page.setSize(page_size);
        page.setCurrent(page_index);
        page.setTotal(count);
        if(null == listMap || listMap.size() == 0){
            return new Response().failure("暂无数据");
        }
        return new Response().success(page);
    }

    @ApiOperation(
            value = "管理员查看单位学员档案列表信息",response = Response.class, produces = "application/json",
            notes = "1、page_index 必填<br>2、page_size 必填<br>3、company_id 必填"
    )
    @TokenSecurity
    @RequestMapping(value = "/userArchiveList", method= RequestMethod.POST)
    public Response userArchiveList(@RequestBody AdminViewDossierJson api)
    {
        String company_id = api.getCompany_id();
        String user_name = api.getUser_name();
        Integer page_index = api.getPage_index();
        Integer page_size = api.getPage_size();
        if(StringUtil.trimAndIsEmpty(company_id)){
            return new Response().failure( "单位编号不能为空");
        }
        if(null == page_index){
            return new Response().failure( "page_index不能为空");
        }
        if(null == page_size){
            return new Response().failure( "page_size不能为空");
        }
        Page<Map<String, Object>> page = new Page<Map<String, Object>>();
        Map map = MapUtil.getInstance();
        map.put("company_id",company_id);
        map.put("user_name", ParamsUtil.joinLike(user_name));
        // 数量
        Integer count = personDossierService.selectCountByParams(map);
        map.put("start_num", (page_index - 1) * page_size);
        map.put("end_num", page_size);
        // 集合
        List<Map<String, Object>> listMap = personDossierService.selectListByParams(map);
        page.setRecords(listMap);
        page.setSize(page_size);
        page.setCurrent(page_index);
        page.setTotal(count);
        if(null == listMap || listMap.size() == 0){
            return new Response().failure("暂无数据");
        }
        return new Response().success(page);
    }

    @ApiOperation(
            value = "2.11管理员首页排行榜接口",response = Response.class, produces = "application/json",
            notes = "company_id 公司编号")
    @TokenSecurity
    @RequestMapping(value = "/rank/page", method= RequestMethod.POST)
    public Response company_order(@RequestBody CompanyOrderJson companyOrderJson){
        String company_id = companyOrderJson.getCompany_id();
        if(StringUtil.trimAndIsEmpty(company_id)){
            return new Response().failure("公司编号不能为空");
        }
//        String ids = companyService.getChildCompanyIds(company_id);
        List<String> companyIds = Arrays.asList(company_id.split(","));
        Map<String, Object> params = MapUtil.getInstance();
        params.put("companyIds",companyIds);
        List<CompanyTj> companyTjs = companyTjService.selectList(companyIds);
        if(null == companyTjs || companyTjs.size() < 1){
            return new Response().success("暂无数据");
        }
        return new Response().success(companyTjs);
    }


    /**
     * 管理员首页统计信息接口
     * @param adminAnalyzeJson
     * @return
     */
    @TokenSecurity
    @ApiOperation(value = "管理员首页统计信息接口",response = Response.class, produces = "application/json")
    @RequestMapping(value = "/analyze", method= RequestMethod.POST)
    public  Response analyze(
            @RequestBody AdminAnalyzeJson adminAnalyzeJson
            ){
        String company_id = adminAnalyzeJson.getCompany_id();
        if("".equals(company_id) || company_id == null){
            return new Response().failure("请求参数为空");
        }

        //根据companyId查询统计信息
        CompanyTj companyTj = companyTjService.analyze(company_id);

        Map resp = MapUtil.getInstance();
        MapUtil.put(resp, "user_count", Integer.valueOf(companyTj.getCount_user()));
        MapUtil.put(resp, "course_count", Integer.valueOf(companyTj.getCount_course()));
        MapUtil.put(resp, "question_count", Integer.valueOf(companyTj.getCount_question()));
        MapUtil.put(resp, "year_train_time", Double.valueOf(companyTj.getTotal_year_class_hour()));
        MapUtil.put(resp, "total_study_time", Double.valueOf(companyTj.getTotal_class_hour()));

        return new Response().success(resp);
    }

}
