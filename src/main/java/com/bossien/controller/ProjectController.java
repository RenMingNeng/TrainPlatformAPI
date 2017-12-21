package com.bossien.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.bossien.common.anno.TokenSecurity;
import com.bossien.common.bean.Response;
import com.bossien.common.util.MapUtil;
import com.bossien.entity.enumeration.ProjectStatusEnum;
import com.bossien.entity.enumeration.ProjectTypeEnum;
import com.bossien.entity.request.UserProjectList;
import com.bossien.entity.request.ViewStudentProjectsJson;
import com.bossien.service.ICompanyProjectService;
import com.bossien.service.IProjectInfoService;
import com.bossien.service.IProjectService;
import com.bossien.service.IProjectUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1.0/project")
@Api(value = "项目")
public class ProjectController {
    @Autowired
    private IProjectUserService projectUserService;
    @Autowired
    private IProjectService projectService;
    @Autowired
    private ICompanyProjectService companyProjectService;
    @Autowired
    private IProjectInfoService projectInfoService;


    /**
     * 我的学习任务列表接口
     * @param userProjectList
     * @return
     */
    @TokenSecurity
    @ApiOperation(value = "查询个人参加的而且是进行中的含培训的项目集合",response = Response.class, produces = "application/json")
    @RequestMapping(value = "/training/page", method= RequestMethod.POST)
    public  Response personalProjectList(
            @RequestBody UserProjectList userProjectList
            ){

        String  user_id = userProjectList.getUser_id();
        String project_status = userProjectList.getProject_status();

        if("".equals(user_id) || user_id == null){
            return new Response().failure("用户Id不能为空");
        }

        if(StringUtils.isEmpty(project_status)){
            return new Response().failure("项目状态不能为空");
        }

        Map map = MapUtil.getInstance();
        map.put("user_id",user_id);

        //查询学员下的所有项目id
        List<String> projectIds = new ArrayList<String>();
        projectIds = projectUserService.selectProjectIds(user_id);
         if(projectIds == null || projectIds.size() == 0){
             return new Response().failure("暂无数据");
         }

        map.put("projectIds",projectIds);
        List<String>  projectTypes = new ArrayList<String>();
        projectTypes.add(ProjectTypeEnum.QuestionType_1.getValue());
        projectTypes.add(ProjectTypeEnum.QuestionType_4.getValue());
        projectTypes.add(ProjectTypeEnum.QuestionType_5.getValue());
        projectTypes.add(ProjectTypeEnum.QuestionType_7.getValue());
        map.put("projectTypes",projectTypes);

        //查询项目集合
        List<Map<String, Object>> projectInfoList = projectInfoService.selectList(map);
        if(projectInfoList.size() == 0){
            return new Response().failure("暂无数据");
        }
        //组装数据
        map.put("project_status",project_status);
        List<Map<String, Object>>  listMap = projectService.handleData(projectInfoList,map);
        if(listMap == null && listMap.size() == 0){
            return new Response().failure("暂无数据");
        }
        return new Response().success(listMap);
    }


    @ApiOperation(value = "管理员查看单位项目列表",response = Response.class, produces = "application/json")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "company_id", value = "单位id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "project_status", value = "项目状态（1：未发布，2：未开始 3：进行中，4：已结束）", required = false, paramType = "query", dataType = "String")
    })
    @TokenSecurity
    @RequestMapping(value = "/viewProjectInfo", method= RequestMethod.POST)
    public  Response viewProjectInfo(
            @RequestParam (value = "company_id", required = true, defaultValue = "") String company_id,
            @RequestParam (value = "project_status", required = true, defaultValue = "") String project_status,
            @RequestParam (value = "page_index", required = true, defaultValue = "1") Integer page_index,
            @RequestParam (value = "page_size", required = true, defaultValue="10")Integer page_size){

        Page<Map<String, Object>> page = new Page<Map<String, Object>>();
        Map map = MapUtil.getInstance();
        map.put("company_id",company_id);
        map.put("project_status",project_status);
        // 查询单位下的所有项目id
        List<String> project_ids = companyProjectService.selectProjectIdsByCompanyId(company_id);
        map.put("project_ids",project_ids);
        // 数量
        Integer count = projectInfoService.selectCountByParams(map);
        map.put("start_num", (page_index - 1) * page_size);
        map.put("end_num", page_size);
        // 结果集
        List<Map<String, Object>> listMap = projectInfoService.selectListByParams(map);
        page.setRecords(listMap);
        page.setSize(page_size);
        page.setCurrent(page_index);
        page.setTotal(count);
        return new Response().success(page);
    }

    /**
     *用户下包含练习考试的项目
     */
    @TokenSecurity
    @ApiOperation(value = "用户下包含练习考试的项目",response = Response.class, produces = "application/json")
    @RequestMapping(value = "/info", method= RequestMethod.POST)
    //@ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),})
    public  Response info(@RequestBody UserProjectList userProjectList){
        if(StringUtils.isEmpty(userProjectList.getUser_id())){
            return new Response().failure("用户编号不能为空");
        }
        if(StringUtils.isEmpty(userProjectList.getProject_status())){
            return new Response().failure("项目状态不能为空");
        }
        Map params = MapUtil.getInstance();
        List<String> projectIds = projectUserService.selectProjectIds(userProjectList.getUser_id());
        if(projectIds == null || projectIds.size() == 0){
            return new Response().failure("暂无数据");
        }
        MapUtil.put(params, "projectIds",projectIds);
        MapUtil.put(params, "project_train",ProjectTypeEnum.QuestionType_1.getValue());
        if((ProjectStatusEnum.ProjectStatus_3.getValue()).equals(userProjectList.getProject_status())){
            MapUtil.put(params, "project_no_start",ProjectStatusEnum.ProjectStatus_2.getValue());
            MapUtil.put(params, "project_start",userProjectList.getProject_status());
        }else if((ProjectStatusEnum.ProjectStatus_4.getValue()).equals(userProjectList.getProject_status())){
            MapUtil.put(params, "project_status",userProjectList.getProject_status());
        }
        //查询项目集合
        List<Map<String, Object>> projectInfoList = projectInfoService.selectList(params);
        if(projectInfoList == null || projectInfoList.size() == 0){
            return new Response().failure("暂无数据");
        }
        List<Map<String, Object>> result = projectService.handleData2(projectInfoList,userProjectList);
        if(result == null || result.size() == 0){
            return new Response().failure("暂无数据");
        }
        return new Response().success(result);
    }

    @TokenSecurity
    @ApiOperation(value = "2.9管理员查看学员的项目列表接口",response = Response.class, produces = "application/json")
    @RequestMapping(value = "/project/analyze", method= RequestMethod.POST)
    public  Response viewStudentProjects(@RequestBody ViewStudentProjectsJson viewStudentProjectsJson){
        String company_id = viewStudentProjectsJson.getCompany_id();
        if(StringUtils.isEmpty(company_id)){
            return new Response().failure("公司编号不能为空");
        }

        String user_id = viewStudentProjectsJson.getUser_id();
        if(StringUtils.isEmpty(user_id)){
            return new Response().failure("用户编号不能为空");
        }

        String project_status = viewStudentProjectsJson.getProject_status();
        if(StringUtils.isEmpty(project_status)){
            project_status = ProjectStatusEnum.ProjectStatus_3.getValue();
        }

        Integer page_index = viewStudentProjectsJson.getPage_index();
        if(null == page_index || page_index == 0){
            page_index = 1;
        }

        Integer page_size = viewStudentProjectsJson.getPage_size();
        if(null == page_size || page_size == 0){
            page_size = 10;
        }

        Page<Map<String, Object>> page = new Page<Map<String, Object>>();
        Map map = MapUtil.getInstance();
        map.put("company_id",company_id);
        map.put("project_status",project_status);

        // 查询单位下的所有项目id
        List<String> projectIds = projectUserService.selectProjectIds(user_id);
        if(null == projectIds || projectIds.size() < 1){
            return new Response().failure("暂无数据");
        }

        // 数量
        map.put("project_ids",projectIds);
        Integer count = projectInfoService.selectCountByParams(map);
        map.put("start_num", (page_index - 1) * page_size);
        map.put("end_num", page_size);

        // 结果集
        List<Map<String, Object>> listMap = projectInfoService.selectListStudentProjectByParams(map, user_id);
        if(null == listMap || listMap.size() < 1){
            return new Response().failure("暂无数据");
        }

        page.setRecords(listMap);
        page.setSize(page_size);
        page.setCurrent(page_index);
        page.setTotal(count);
        return new Response().success(page);
    }
}
