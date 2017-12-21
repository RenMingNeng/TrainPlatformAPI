package com.bossien.controller;

import com.bossien.common.anno.TokenSecurity;
import com.bossien.common.bean.Response;
import com.bossien.entity.QuestionCollection;
import com.bossien.entity.QuestionWrongAnswers;
import com.bossien.entity.User;
import com.bossien.entity.request.QuestionCollectJson;
import com.bossien.entity.request.QuestionRemoveJson;
import com.bossien.entity.request.UserRankJson;
import com.bossien.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 题目控制器
 * @author chengcheng.luo
 * @date 2017年10月17日 上午11:26:00
 */
@RestController
@RequestMapping("/api/v1.0/question")
@Api(value = "题目")
public class QuestionController {

	// 错题表
	@Value("${mongo_db_question_wrong_answers}")
	private String mongo_db_question_wrong_answers;

	// 收藏表
	@Value("${mongo_db_question_collection}")
	private String mongo_db_question_collection;

	// 动作-新增
	@Value("${action_type_add}")
	private String action_type_add;

	// 动作-取消
	@Value("${action_type_cancel}")
	private String action_type_cancel;

	@Autowired
	private IUserService userService;

	@Autowired
	private MongoOperations mongoTemplate;


	/**
	 * 移除错题
	 * @return
	 */
	@ApiOperation(
			value = "移除错题",response = Response.class,
			notes = "1、user_id 账户id-必填<br>2、project_id 项目id-必填<br>3、question_id 题目id-必填"
	)
//	@ApiImplicitParams(value = {
//			@ApiImplicitParam(name = "user_id", value = "用户id", required = true, paramType = "query", dataType = "String"),
//			@ApiImplicitParam(name = "project_id", value = "项目id", required = true, paramType = "query", dataType = "String"),
//			@ApiImplicitParam(name = "question_id", value = "题目id", required = true, paramType = "query", dataType = "String")
//	})
	@TokenSecurity
	@RequestMapping(value = "/remove", method=RequestMethod.POST)
    public  Response logout(
//			@RequestParam(value = "user_id", required = true) String user_id,
//			@RequestParam(value = "project_id", required = true) String project_id,
//			@RequestParam(value = "question_id", required = true) String question_id
			@RequestBody QuestionRemoveJson questionRemoveJson
			){

		String user_id = questionRemoveJson.getUser_id();
		String project_id = questionRemoveJson.getProject_id();
		String question_id = questionRemoveJson.getQuestion_id();

		if(StringUtils.isEmpty(user_id)) {
			return new Response().failure( "user_id不能为空");
		}

		if(StringUtils.isEmpty(project_id)) {
			return new Response().failure( "project_id不能为空");
		}

		if(StringUtils.isEmpty(question_id)) {
			return new Response().failure( "question_id不能为空");
		}

		// 查询错题
		Query query = new Query(new Criteria().andOperator(
				Criteria.where("user_id").is(user_id),
				Criteria.where("project_id").is(project_id),
				Criteria.where("question_id").is(question_id)
		));
		// 移除错题
		mongoTemplate.remove(query, QuestionWrongAnswers.class, mongo_db_question_wrong_answers);
		return new Response().success("移除成功");
    }

	/**
	 * 收藏/取消收藏
	 * @return
	 */
	@ApiOperation(
			value = "收藏/取消收藏",response = Response.class,
			notes = "1、user_id 账户id-必填<br>2、project_id 项目id-必填<br>3、question_id 题目id-必填<br>4、action=add表示收藏、action=cancel表示取消收藏"
	)
//	@ApiImplicitParams(value = {
//			@ApiImplicitParam(name = "user_id", value = "用户id", required = true, paramType = "query", dataType = "String"),
//			@ApiImplicitParam(name = "project_id", value = "项目id", required = true, paramType = "query", dataType = "String"),
//			@ApiImplicitParam(name = "question_id", value = "题目id", required = true, paramType = "query", dataType = "String"),
//			@ApiImplicitParam(name = "action", value = "收藏/取消收藏", required = true, paramType = "query", dataType = "String")
//	})
	@TokenSecurity
	@RequestMapping(value = "/collect", method=RequestMethod.POST)
	public  Response logout(
//			@RequestParam(value = "user_id", required = true) String user_id,
//			@RequestParam(value = "project_id", required = true) String project_id,
//			@RequestParam(value = "question_id", required = true) String question_id,
//			@RequestParam(value = "action", required = true) String action
			@RequestBody QuestionCollectJson questionCollectJson
	){

		String user_id = questionCollectJson.getUser_id();
		String project_id = questionCollectJson.getProject_id();
		String question_id = questionCollectJson.getQuestion_id();
		String action = questionCollectJson.getAction();

		if(StringUtils.isEmpty(user_id)) {
			return new Response().failure( "user_id不能为空");
		}

		if(StringUtils.isEmpty(project_id)) {
			return new Response().failure( "project_id不能为空");
		}

		if(StringUtils.isEmpty(question_id)) {
			return new Response().failure( "question_id不能为空");
		}

		if(StringUtils.isEmpty(action)) {
			return new Response().failure( "action不能为空");
		}

		// 校验action
		if(!action_type_add.equals(action) && !action_type_cancel.equals(action)) {
			return new Response().failure( "参数action允许值域为['add', 'cancel']");
		}

		// 查询条件
		Query query = new Query(new Criteria().andOperator(
				Criteria.where("user_id").is(user_id),
				Criteria.where("project_id").is(project_id),
				Criteria.where("question_id").is(question_id)
		));

		// 查询收藏题目
		QuestionCollection questionCollection = mongoTemplate.findOne(query, QuestionCollection.class, mongo_db_question_collection);

		// 收藏
		if(action_type_add.equals(action) && null == questionCollection) {
			User user = userService.getOne(user_id);
			questionCollection = new QuestionCollection(
					project_id,
					question_id,
					user_id,
					null == user ? "" : user.getUser_name(),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
			);
			mongoTemplate.insert(questionCollection, mongo_db_question_collection);
			return new Response().success("收藏成功");
		}
		if(action_type_add.equals(action) && null != questionCollection) {
			// 已经收藏  不做任何处理
			return new Response().success("收藏成功");
		}
		// 取消收藏
		if(action_type_cancel.equals(action) && null != questionCollection) {
			mongoTemplate.remove(questionCollection, mongo_db_question_collection);
			return new Response().success("取消收藏成功");
		}
		if(action_type_cancel.equals(action) && null == questionCollection) {
			// 未收藏  不做任何处理
			return new Response().success("取消收藏成功");
		}

		return new Response().failure( "操作失败");
	}
}
