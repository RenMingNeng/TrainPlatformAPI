package com.bossien.plugin.token;

import com.bossien.common.anno.TokenSecurity;
import com.bossien.common.bean.Response;
import com.bossien.plugin.redis.JedisClient;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Token验证
 * @author Administrator
 *
 */
@Aspect
@Component
public class SecurityAspect {

	public static final Logger logger = LoggerFactory.getLogger(SecurityAspect.class);
	private static final String DEFAULT_TOKEN_NAME = "X-Token";

	@Autowired
	private JedisClient jedisClient;
	@Resource(name="redisTokenManager") private TokenManager tokenManager;
	
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void controllerAspect() {
		
	}
	
	/**
	 * 接收到客户端请求时执行
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("controllerAspect()")
	public Object execute(ProceedingJoinPoint pjp) throws Throwable {
		// 从切点上获取目标方法
		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		Method method = methodSignature.getMethod();
		/**
		 * 验证Token
		 */
		if (method.isAnnotationPresent(TokenSecurity.class)) {
			// 从 request header 中获取当前 token
			HttpServletResponse resp = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
			//String token = 	request.getHeader(DEFAULT_TOKEN_NAME);
			String userId=request.getHeader("userId");
			logger.info("客户端获取的userId值是======="+userId);
			String tokenOld=request.getHeader(DEFAULT_TOKEN_NAME);
			logger.info("客户端获取的token值是======="+tokenOld);
			if(StringUtils.isEmpty(tokenOld)){
				throw new TokenException("客户端X-Token参数不能为空,且从Header中传入,如果没有登录,请先登录获取Token");
			}
			// 检查 token 有效性
			/*if (!tokenManager.checkToken(userId)) {
				String message = String.format("Token [%s] 非法", userId);
				throw new TokenException(message);
			}*/

			if(userId!=null){
				//根据userId在redis里面获取token值
				String tokenNew = jedisClient.get("app_token_"+userId);
				logger.info("redis获取的token值是======="+tokenNew);
				//token不一致 则提示用户已经登录
					if(tokenNew==null||!tokenNew.equals(tokenOld)){
						logger.info("用户是重复登录=======");
						return 	new Response().failure("repeat_login");
					}
			}else {
				logger.info("没有userId==========");
				//throw new TokenException("没有userId");
			}


		}
		
		// 调用目标方法
		return pjp.proceed();
	}
}
