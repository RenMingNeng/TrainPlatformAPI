package com.bossien.plugin.token;

import com.bossien.plugin.redis.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;
/**
 * Redis Token存储实现
 * @author Gaojun.Zhou
 * @date 2016年12月29日 下午2:31:08
 */
@Component
public class RedisTokenManager  implements TokenManager {

	@Autowired private JedisClient jedisClient;

	/**
	 * 存储15天
	 */
	public static int timeOut = 60 * 60 * 24 * 15;
	
	/**
	 * 创建Token
	 */
    @Override
    public String createToken(String uid) {
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        jedisClient.set("app_token_"+uid,token,timeOut);
        return token;
    }
    /**
     * 验证Token
     */
    @Override
    public boolean checkToken(String uid) {
    	
        return !StringUtils.isEmpty(jedisClient.get("app_token_"+uid));
    }

    /**
     * 删除Tokean
     *
     * @param uid
     */
    @Override
    public boolean delToken(String uid) {
        long result =  jedisClient.del(uid);
        return result > 0;
    }
}
