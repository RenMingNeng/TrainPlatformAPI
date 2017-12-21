package com.bossien.common.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * csl
 * Created by Administrator on 2017/11/29.
 */
public class JsonUtil {

    /**
     * json string转换为map对象
     * @param jsonObj sonObject对象
     * @return 返回
     */
    public static Map<String,Object> jsonToMap(Object jsonObj){
        JSONObject jsonObject=JSONObject.fromObject(jsonObj);
        Map<String,Object> map = (Map) jsonObject;
        return map;
    }

    /**
     * json array转换为list<Map>
     * @param jsonObj  sonObject对象
     * @return 返回
     */
    public static List<Map<String,Object>> jsonArrayToMap(Object jsonObj){
        JSONArray jsonObject = JSONArray.fromObject(jsonObj);
        List<Map<String,Object>> list = (List) jsonObject;
        return list;
    }
    /**
     * json string 转换为对象
     * @param jsonObj jsonObject对象
     * @param tclass 实体类
     * @param <T> 范形
     * @return 返回值
     */
    public static<T> T jsonToBean(Object jsonObj,Class<T> tclass){
        JSONObject jsonObject=JSONObject.fromObject(jsonObj);
        T obj = (T) JSONObject.toBean(jsonObject, tclass);
        return obj;
    }
}
