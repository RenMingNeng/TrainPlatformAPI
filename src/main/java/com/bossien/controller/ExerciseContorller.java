package com.bossien.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.bossien.common.anno.TokenSecurity;
import com.bossien.common.bean.Response;
import com.bossien.common.util.MapUtil;
import com.bossien.entity.request.ExerciseCourseQuestion;
import com.bossien.entity.request.ExerciseTypeList;
import com.bossien.entity.request.PerfectExercise;
import com.bossien.entity.request.QuestionTypeList;
import com.bossien.service.ICourseExerciseService;
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
 * Created by Administrator on 2017/10/18.
 */
@RestController
@RequestMapping("/api/v1.0/exercise")
@Api(value = "课程练习")
public class ExerciseContorller {

    @Autowired
    private ICourseExerciseService courseExerciseService;


    @TokenSecurity
    @ApiOperation(value = "根据课程查询课程下面的试题信息",response = Response.class)
    @RequestMapping(value = "/userCourseQuestion",method= RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),})
    public Response userCourseQuestion(@RequestBody ExerciseCourseQuestion cq){
        if("".equals(cq.getUser_id()) || cq.getUser_id()==null){
            return new Response().failure("用户Id不能为空");
        }
        /*if("".equals(cq.getProject_id()) || cq.getProject_id()==null){
            return new Response().failure("项目Id不能为空");
        }*/
        if("".equals(cq.getCourse_id()) || cq.getCourse_id()==null){
            return new Response().failure("课程Id不能为空");
        }
        Map<String, Object> params= courseExerciseService.selectCourseQuestion(new Page<Map<Object, Object>>(cq.getPage_index(),cq.getPage_size()),cq);
        if(null == params || params.isEmpty()){
            return new Response().failure("暂无数据");
        }
        return new Response().success(params);
    }

    /**
     * 顺序练习接口
     * @param exerciseTypeList
     * @return
     */
    @TokenSecurity
    @ApiOperation(value = "顺序练习接口",response = Response.class, produces = "application/json")
    @RequestMapping(value = "/page", method=RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),})
    public  Response page(@RequestBody ExerciseTypeList exerciseTypeList){
        if(StringUtils.isEmpty(exerciseTypeList.getProject_id())){
            return new Response().failure("项目编号不能为空");
        }
        if(StringUtils.isEmpty(exerciseTypeList.getUser_id())){
            return new Response().failure("用户编号不能为空");
        }
        Map map = MapUtil.getInstance();
        MapUtil.put(map,"project_id",exerciseTypeList.getProject_id());
        MapUtil.put(map,"user_id",exerciseTypeList.getUser_id());
        Map<String, Object> params= courseExerciseService.selectAllQuestion(new Page<Map<Object, Object>>(exerciseTypeList.getPage_index(),exerciseTypeList.getPage_size()),map);
        if(null == params){
            return new Response().failure("暂无数据");
        }
        return new Response().success(params);

    }

    /**
     * 未做题练习接口
     * @param exerciseTypeList
     * @return
     */
    @TokenSecurity
    @ApiOperation(value = "未做题练习接口",response = Response.class, produces = "application/json")
    @RequestMapping(value = "/undone/page", method=RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),})
    public  Response continues(@RequestBody ExerciseTypeList exerciseTypeList){
        if(StringUtils.isEmpty(exerciseTypeList.getProject_id())){
            return new Response().failure("项目编号不能为空");
        }
        if(StringUtils.isEmpty(exerciseTypeList.getUser_id())){
            return new Response().failure("用户编号不能为空");
        }
        Map map = MapUtil.getInstance();
        MapUtil.put(map,"project_id",exerciseTypeList.getProject_id());
        MapUtil.put(map,"user_id",exerciseTypeList.getUser_id());
        Map<String, Object> params = courseExerciseService.selectNoExerciseQuestion(new Page<Map<Object, Object>>(exerciseTypeList.getPage_index(),exerciseTypeList.getPage_size()),map);
        if(null == params){
            return new Response().failure("暂无数据");
        }
        return new Response().success(params);

    }

    @TokenSecurity
    @ApiOperation(value = "查询该用户专项练习列表",response = Response.class)
    @RequestMapping(value = "/exerciseList",method=RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),})
    public Response exerciseList(@RequestBody PerfectExercise per){
        if(StringUtils.isEmpty(per.getUser_id())){
            return new Response().failure("用户Id不能为空");
        }
        if(StringUtils.isEmpty((per.getProject_id()))){
            return new Response().failure("项目Id不能为空");
        }
        List<Map<String, Object>> result = courseExerciseService.selectExerciseMap(per);
        if(null == result || result.size() < 1){
            return new Response().failure("暂无数据");
        }
        return new Response().success(result);
    }

    @TokenSecurity
    @ApiOperation(value = "根据题目类型查询试题列表",response = Response.class)
    @RequestMapping(value = "/questionTypeList",method=RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),})
    public Response exerciseTypeList(@RequestBody QuestionTypeList qtl){
        if(StringUtils.isEmpty(qtl.getUser_id())){
            return new Response().failure("用户Id不能为空");
        }
        if(StringUtils.isEmpty(qtl.getProject_id())){
            return new Response().failure("项目Id不能为空");
        }
        if(StringUtils.isEmpty(qtl.getQuestions_type())){
            return new Response().failure("专项练习试题类型不能为空");
        }
        if(qtl.getQuestions_count()==null){
            return new Response().failure("试题数量不能为空");
        }
        Map<String, Object> params = courseExerciseService.selectQuestionTypeExerciseMap(new Page<Map<Object, Object>>(qtl.getPage_index(),qtl.getPage_size()),qtl);
        if(null == params || params.isEmpty()){
            return new Response().failure("暂无数据");
        }
        return new Response().success(params);
    }
}
