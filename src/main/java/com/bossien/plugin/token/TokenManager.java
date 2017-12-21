package com.bossien.plugin.token;
/**
 * Token接口
 * @author Gaojun.Zhou
 * @date 2016年12月29日 下午2:34:00
 */
public interface TokenManager {
	
	/**
	 * 生成Tokean
	 */
	String createToken(String uid);
	/**
	 * 检查Token有效性
	 */
	boolean checkToken(String token);
	/**
	 * 删除Tokean
	 */
	boolean delToken(String token);
}
