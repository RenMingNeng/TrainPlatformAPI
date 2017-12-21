package com.bossien.controller;

import com.bossien.common.anno.TokenSecurity;
import com.bossien.common.bean.Response;
import com.bossien.common.util.MapUtil;
import com.bossien.common.util.QuestionsUtils;
import com.bossien.common.util.ValidateUtil;
import com.bossien.entity.*;
import com.bossien.entity.request.UserCollectsJson;
import com.bossien.entity.request.UserInfoJson;
import com.bossien.entity.request.UserRankJson;
import com.bossien.entity.request.UserWrongsJson;
import com.bossien.plugin.page.SpringDataPageable;
import com.bossien.service.*;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 登录控制器
 * @author chengcheng.luo
 * @date 2017年10月17日 上午11:26:00
 */
@RestController
@RequestMapping("/api/v1.0/user")
@Api(value = "用户")
public class UserController {

	// 错题表
	@Value("${mongo_db_question_wrong_answers}")
	private String mongo_db_question_wrong_answers;

	// 收藏表
	@Value("${mongo_db_question_collection}")
	private String mongo_db_question_collection;

	@Autowired
	private MongoOperations mongoTemplate;
	@Autowired
	private IProjectStatisticsInfoService projectStatisticsInfoService;
	@Autowired
	private IUserService userService;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private IQuestionService questionService;
	@Autowired
	private IUserTrainRoleService userTrainRoleService;
	@Autowired
	private ICourseQuestionService courseQuestionService;
	@Autowired
	private IQuestionCollectionService questionCollectionService;
	@Autowired
	private IQuestionWrongAnswersService questionWrongAnswersService;


	@ApiOperation(
			value = "我的错题,返回错误的题目结果集",response = Response.class,
			notes = "1、page_index 当前页数-必填<br>2、page_size 每页条数-必填<br>3、user_id 账户id-必填<br>4、project_id 项目id-必填")
//	@ApiImplicitParams(value = {
//			@ApiImplicitParam(name = "page_index", value = "当前页数", required = true, paramType = "query", dataType = "String"),
//			@ApiImplicitParam(name = "page_size", value = "每页条数", required = true, paramType = "query", dataType = "String"),
//			@ApiImplicitParam(name = "user_id", value = "账户id", required = true, paramType = "query", dataType = "String"),
//			@ApiImplicitParam(name = "project_id", value = "项目id", required = true, paramType = "query", dataType = "String")
//	})
	@TokenSecurity
	@RequestMapping(value = "/wrongs", method=RequestMethod.POST)
	public  Response wrongs(
//			@RequestParam(value = "page_index", required = true, defaultValue = "1") Integer page_index,
//			@RequestParam(value = "page_size", required = true, defaultValue = "10") Integer page_size,
//			@RequestParam(value = "user_id", required = true) String user_id,
//			@RequestParam(value = "project_id", required = true) String project_id
			@RequestBody UserWrongsJson userWrongsJson
	){

		Integer page_index = userWrongsJson.getPage_index();
		Integer page_size = userWrongsJson.getPage_size();
		String user_id = userWrongsJson.getUser_id();
		String project_id = userWrongsJson.getProject_id();

		if(null == page_index) {
			return new Response().failure("page_index不能为空");
		}

		if(null == page_size) {
			return new Response().failure("page_size不能为空");
		}

		if(StringUtils.isEmpty(user_id)) {
			return new Response().failure("user_id不能为空");
		}

		if(StringUtils.isEmpty(project_id)) {
			return new Response().failure("project_id不能为空");
		}

		if(!ValidateUtil.maxLength(page_size, 1000)) {
			return new Response().failure("每页个数不超过1000");
		}

		// 查询分页条件
		SpringDataPageable pageable = new SpringDataPageable();
		// 查询条件
		Query query = new Query(new Criteria().andOperator(
				Criteria.where("project_id").is(project_id),
				Criteria.where("user_id").is(user_id)
		));
		// 默认按时间降序
		List<Sort.Order> orders = Lists.newArrayList();
		orders.add(new Sort.Order(Sort.Direction.DESC, "create_time"));
		Sort sort = new Sort(orders);
		// 页码
		pageable.setPageNumber(page_index);
		// 每页条数
		pageable.setPageSize(page_size);
		// 排序
		pageable.setSort(sort);
		// 总条数
		Long total = mongoTemplate.count(query, QuestionWrongAnswers.class, mongo_db_question_wrong_answers);
		// 结果集
		List<QuestionWrongAnswers> questionWrongAnswerss = mongoTemplate.find(query.with(pageable), QuestionWrongAnswers.class, mongo_db_question_wrong_answers);
		List<Question> questions = questionService.selectByIds(questionWrongAnswersService.getIds(questionWrongAnswerss));
		// 构造分页
//		Page<Question> page = new Page<Question>();
//		page.setRecords(questions);
//		page.setSize(page_size);
//		page.setCurrent(page_index);page.setTotal(total.intValue());
//
//		return new Response().success(page);

		// Question list 转换
		List<Map<String, Object>> datas = QuestionsUtils.combinQuestion(questions,
				courseQuestionService.selectCourseIdByQuestionId(project_id, user_id),
				null);

		Map resp = MapUtil.getInstance();
		MapUtil.put(resp, "user_id", user_id);
		MapUtil.put(resp, "project_id", project_id);
		MapUtil.put(resp, "datas", datas);
		MapUtil.put(resp, "count", total.intValue());
		return new Response().success(resp);
	}

	@ApiOperation(
			value = "我的收藏,返回收藏的题目结果集",response = Response.class,
			notes = "1、page_index 当前页数-必填<br>2、page_size 每页条数-必填<br>3、user_id 账户id-必填<br>4、project_id 项目id-必填")
//	@ApiImplicitParams(value = {
//			@ApiImplicitParam(name = "page_index", value = "当前页数", required = true, paramType = "query", dataType = "String"),
//			@ApiImplicitParam(name = "page_size", value = "每页条数", required = true, paramType = "query", dataType = "String"),
//			@ApiImplicitParam(name = "user_id", value = "账户id", required = true, paramType = "query", dataType = "String"),
//			@ApiImplicitParam(name = "project_id", value = "项目id", required = true, paramType = "query", dataType = "String")
//	})
	@TokenSecurity
	@RequestMapping(value = "/collects", method=RequestMethod.POST)
	public  Response collects(
//			@RequestParam(value = "page_index", required = true, defaultValue = "1") Integer page_index,
//			@RequestParam(value = "page_size", required = true, defaultValue = "10") Integer page_size,
//			@RequestParam(value = "user_id", required = true) String user_id,
//			@RequestParam(value = "project_id", required = true) String project_id
			@RequestBody UserCollectsJson userCollectsJson
			){

		Integer page_index = userCollectsJson.getPage_index();
		Integer page_size = userCollectsJson.getPage_size();
		String user_id = userCollectsJson.getUser_id();
		String project_id = userCollectsJson.getProject_id();

		if(null == page_index) {
			return new Response().failure("page_index不能为空");
		}

		if(null == page_size) {
			return new Response().failure("page_size不能为空");
		}

		if(StringUtils.isEmpty(user_id)) {
			return new Response().failure("user_id不能为空");
		}

		if(StringUtils.isEmpty(project_id)) {
			return new Response().failure("project_id不能为空");
		}

		if(!ValidateUtil.maxLength(page_size, 1000)) {
			return new Response().failure("每页个数不超过1000");
		}

		// 查询分页条件
		SpringDataPageable pageable = new SpringDataPageable();
		// 查询条件
		Query query = new Query(new Criteria().andOperator(
				Criteria.where("project_id").is(project_id),
				Criteria.where("user_id").is(user_id)
		));
		// 默认按时间降序
		List<Sort.Order> orders = Lists.newArrayList();
		orders.add(new Sort.Order(Sort.Direction.DESC, "create_time"));
		Sort sort = new Sort(orders);
		// 页码
		pageable.setPageNumber(page_index);
		// 每页条数
		pageable.setPageSize(page_size);
		// 排序
		pageable.setSort(sort);
		// 总条数
		Long total = mongoTemplate.count(query, QuestionCollection.class, mongo_db_question_collection);
		// 结果集
		List<QuestionCollection> questionCollections = mongoTemplate.find(query.with(pageable), QuestionCollection.class, mongo_db_question_collection);
		List<Question> questions = questionService.selectByIds(questionCollectionService.getIds(questionCollections));
		// 构造分页
//		Page<Question> page = new Page<Question>();
//		page.setRecords(questions);
//		page.setSize(page_size);
//		page.setCurrent(page_index);page.setTotal(total.intValue());
//
//		return new Response().success(page);

		// Question list 转换
		List<Map<String, Object>> datas = QuestionsUtils.combinQuestion(questions,
				courseQuestionService.selectCourseIdByQuestionId(project_id, user_id),
				null);

		Map resp = MapUtil.getInstance();
		MapUtil.put(resp, "user_id", user_id);
		MapUtil.put(resp, "project_id", project_id);
		MapUtil.put(resp, "datas", datas);
		MapUtil.put(resp, "count", total.intValue());
		return new Response().success(resp);
	}

	@ApiOperation(
			position = 2,
			value = "个人信息",response = Response.class,
			notes = "1、user_id 账户id-必填<br>2、查询个人信息返回包括：姓名、账号、账号id、单位id、单位名称、受训角色")
//	@ApiImplicitParams(value = {
//			@ApiImplicitParam(name = "user_id", value = "账户id", required = true, paramType = "query", dataType = "String")
//	})
	@TokenSecurity
	@RequestMapping(value = "/info", method=RequestMethod.POST)
	public  Response info(
//			@RequestParam(value = "user_id", required = true) String user_id
			@RequestBody UserInfoJson userInfoJson
	){

		String user_id = userInfoJson.getUser_id();

		if(StringUtils.isEmpty(user_id)) {
			return new Response().failure("user_id不能为空");
		}

		Map resp = MapUtil.getInstance();
		// 查询user
		User user = userService.getOne(user_id);
		// 校验user
		if(null == user) {
			return new Response().failure("用户不存在");
		}
		// 查询company
		Company company = companyService.selectByIntId(user.getCompany_id());
		// 查询user_train_role_list
		List<UserTrainRole> userTrainRoles = userTrainRoleService.selectList(new UserTrainRole(user_id));
		// 封装resp
		MapUtil.put(resp, "user_id", user_id);
		MapUtil.put(resp, "user_name", user.getUser_name());
		MapUtil.put(resp, "user_account", user.getUser_account());
		MapUtil.put(resp, "company_id", user.getCompany_id());
		MapUtil.put(resp, "company_name", null == company ? "" : company.getVarName());
		MapUtil.put(resp, "department_id", user.getDepartment_id());
		MapUtil.put(resp, "department_name", user.getDepartment_name());
		MapUtil.put(resp, "train_role_names", userTrainRoleService.getNames(userTrainRoles));

		return new Response().success(resp);
	}


	/**
	 * 个人信息-我的排行接口
	 * @param userRankJson
	 * @return
	 */
	@TokenSecurity
	@ApiOperation(value = "个人信息-我的排行",response = Response.class, produces = "application/json")
	@RequestMapping(value = "/rank", method= RequestMethod.POST)
	public  Response rank(
			@RequestBody UserRankJson userRankJson
			){
		String user_id = userRankJson.getUser_id();

		if(StringUtils.isEmpty(user_id)) {
			return new Response().failure("user_id不能为空");
		}

		Map params = MapUtil.getInstance();

		//根据user_id 查询用户信息
		params.put("user_id",user_id);
		User user = userService.getOne(user_id);
		if(null == user ){
			return new Response().failure("用户不存在");
		}


		//查询同公司下的userIds集合
		params.put("company_id",user.getCompany_id());
		List<String> userIds_company = userService.selectUserIds(params);
		if(userIds_company.size()<= 0){
			return new Response().failure("公司下没有学员");
		}

		//公司下的排行
		params.put("user_ids",userIds_company);
		params.put("userid",user_id);
		Integer companyRank = projectStatisticsInfoService.selectRankByTotalStudyTime(params);
		if(null == companyRank){
			return new Response().failure("该学员没有排名");
		}

		//查询同公司同部门下的userIds集合
		params.put("department_id",user.getDepartment_id());
		List<String> userIds_department = userService.selectUserIds(params);
		if(userIds_department.size()<= 0){
			return new Response().failure("单位下没有学员");
		}

		//部门下的排行
		params.put("user_ids",userIds_department);
		Integer deptRank = projectStatisticsInfoService.selectRankByTotalStudyTime(params);


		params.clear();
		params.put("user_id",user_id);
		Map resp = MapUtil.getInstance();
		MapUtil.put(resp, "study_total_time",projectStatisticsInfoService.selectTotalClassHour(params));
		MapUtil.put(resp, "com_rank", companyRank);
		MapUtil.put(resp, "dep_rank", deptRank);

		return new Response().success(resp);

	}


}
