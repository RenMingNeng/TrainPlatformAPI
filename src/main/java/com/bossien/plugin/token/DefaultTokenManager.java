package com.bossien.plugin.token;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 默认Token实现方式为内存Token
 * @author Administrator
 *
 */
@Component
public class DefaultTokenManager  implements TokenManager {

    private static Map<String, String> tokenMap = new ConcurrentHashMap<String, String>();

    @Override
    public String createToken(String uid) {
        String token = UUID.randomUUID().toString();
        tokenMap.put("app_token_"+uid, token);
        return token;
    }

    @Override
    public boolean checkToken(String uid) {

        return !StringUtils.isEmpty(uid) && tokenMap.containsKey("app_token_"+uid);
    }

    /**
     * 删除Tokean
     *
     * @param uid
     */
    @Override
    public boolean delToken(String uid) {
        tokenMap.remove(uid);
        return true;
    }
}
