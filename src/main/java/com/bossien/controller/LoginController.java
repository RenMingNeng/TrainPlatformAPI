package com.bossien.controller;

import com.bossien.common.bean.Response;
import com.bossien.common.util.MapUtil;
import com.bossien.common.util.ValidateUtil;
import com.bossien.entity.Company;
import com.bossien.entity.User;
import com.bossien.entity.UserRole;
import com.bossien.entity.request.UserLoginJson;
import com.bossien.entity.request.UserLogoutJson;
import com.bossien.listener.Event001;
import com.bossien.plugin.redis.JedisClient;
import com.bossien.plugin.token.TokenManager;
import com.bossien.service.ICompanyService;
import com.bossien.service.IUserRoleService;
import com.bossien.service.IUserService;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 登录控制器
 * @author chengcheng.luo
 * @date 2017年10月17日 上午11:26:00
 */
@RestController
@RequestMapping("/api/v1.0")
@Api(value = "用户")
public class LoginController {

	/**
	 * Token服务
	 */
	@Resource(name="redisTokenManager") private TokenManager tokenManager;

	@Autowired
	private IUserService userService;
	@Autowired
	private IUserRoleService userRoleService;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private JedisClient jedisClient;

	@ApiOperation(
			value = "登陆,返回给客户端Token,用于安全校验,需要客户端保存起来",response = Response.class,
			notes = "1、user_account账户不少于6位<br>2、user_passwd密码需要在客户端MD5处理<br>3、user_type为人员类型{1：培训组织人员；2：培训监督人员 3学员}")
//	@ApiImplicitParams(value = {
//			@ApiImplicitParam(name = "user_type", value = "类型", required = true, paramType = "query", dataType = "String"),
//			@ApiImplicitParam(name = "user_account", value = "账户", required = true, paramType = "query", dataType = "String"),
//			@ApiImplicitParam(name = "user_passwd", value = "密码", required = true, paramType = "query", dataType = "String")
//	})
    @RequestMapping(value = "/login", method=RequestMethod.POST)
    public  Response login(HttpServletRequest request,
//			@RequestParam(value = "user_type", required = true) String user_type,
//    		@RequestParam(value = "user_account", required = true) String user_account,
//		    @RequestParam(value = "user_passwd", required = true) String user_passwd
			@RequestBody UserLoginJson userLoginJson
		){

		String user_type = userLoginJson.getUser_type();
		String user_account = userLoginJson.getUser_account();
		String user_passwd = userLoginJson.getUser_passwd();

		if(!"1".equals(user_type) && !"2".equals(user_type) && !"3".equals(user_type)) {
			return new Response().failure( "user_type 被允许的值域 [1, 2, 3]");
		}

/*		if(!ValidateUtil.minLength(user_account, 6)) {
			return new Response().failure( "账户不少于6位");
		}*/

		User user = null;

		// 账户是否存在
		user = userService.selectOne(new User(user_account));
		if(null == user) {
			return new Response().failure( "账户不存在");
		}

		// 账户是否锁定
		if(!User.IsValid.TYPE_1.getValue().equals(user.getIs_valid())) {
			return new Response().failure( "账号被锁定");
		}

		// 账户有无归属单位
		String company_id = user.getCompany_id();
		if(StringUtils.isEmpty(company_id)) {
			return new Response().failure( "无归属单位");
		}

		// 账户单位是否存在
		Company company = companyService.selectByIntId(company_id);
		if(null == company) {
			return new Response().failure( "无归属单位");
		}

		// 账户单位是否有效
		if(!Company.ChrIsValid.TYPE_1.getValue().equals(company.getChrIsValid())) {
			return new Response().failure( "归属单位被禁用");
		}

		// 账户密码是否正确
		user = userService.selectOne(new User(user_account, user_passwd));
		if(null == user) {
			return new Response().failure( "密码错误");
		}

		UserRole userRole = null;
		// 账户角色是否正确
		userRole = userRoleService.selectOne(new UserRole(user.getId(), userService.getRoleIdByUserType(user_type)));
		if(null == userRole) {
			return new Response().failure( "角色不存在");
		}

		// 校验通过, 为app分配token
		String token = tokenManager.createToken(user.getId());
		// 方便返回用户的单位名称
		user.setCompany_name(company.getVarName());

		//修改公司下到时间的项目的状态
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		Map<String, Object> params = Maps.newConcurrentMap();
		params.put("company_id", company.getIntId());
		Event001 event001 = new Event001(params);
		wac.publishEvent(event001);

		// 封装resp
		Map resp = MapUtil.getInstance();
		MapUtil.put(resp, "user", user);
		MapUtil.put(resp, "token", token);

		return new Response().success(resp);

    }

	/**
	 * 退出系统
	 * @return
	 */
	@ApiOperation(
			value = "登出",response = Response.class,
			notes = "1、注销token<br>2、客户端调用后需强制退回登陆页")
//	@ApiImplicitParams(value = {
//			@ApiImplicitParam(name = "token", value = "秘钥", required = true, paramType = "query", dataType = "String")
//	})
	@RequestMapping(value = "/logout", method=RequestMethod.POST)
    public  Response logout(
//			@RequestParam(value = "token", required = true) String token
            @RequestBody UserLogoutJson userLogoutJson
		){

	    String token = userLogoutJson.getToken();
	    if(StringUtils.isEmpty(token)) {
            return new Response().failure("token不能为空");
        }
		// 注销token
		tokenManager.delToken(token);
		return new Response().success("登出成功");
    }  
}
