package com.bossien.service.impl;

import com.bossien.common.util.DateUtils;
import com.bossien.common.util.MapUtil;
import com.bossien.entity.User;
import com.bossien.service.IBaseService;
import com.bossien.service.ISequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;

/**
 * Created by A on 2017/8/1.
 */
@Service
public class BaseServiceImpl<T> implements IBaseService<T> {
    @Autowired
    private ISequenceService sequenceService;


    /**
     * 初始化基本参数
     * @param user
     * @param t 初始化后的对象
     * @return
     * @throws Exception
     */
    public T build(User user, T t){

        return build(user.getUser_name(), t);
    }

    /**
     * 初始化基本参数
     * @param user_name
     * @param t 初始化后的对象
     * @return
     * @throws Exception
     */
    public T build(String user_name, T t){
        try{
            Field[] fields = t.getClass().getDeclaredFields();
            for (Field field : fields){
                field.setAccessible(true);
                if(field.getName().equals("createUser") || field.getName().equals("operUser")
                        || field.getName().equals("varCreateUser") || field.getName().equals("varOperUser")
                        || field.getName().equals("create_user") || field.getName().equals("oper_user")){
                    field.set(t, user_name);
                }

                if(field.getName().equals("createTime") || field.getName().equals("operTime")
                        || field.getName().equals("datCreateDate") || field.getName().equals("datOperDate")
                        || field.getName().equals("create_time") || field.getName().equals("oper_time")){
                    field.set(t, DateUtils.formatDateTime(new Date()));
                }

                if(field.getName().equals("id")){
                    field.set(t, sequenceService.generator());
                }
            }
        } catch (Exception e){
            throw new RuntimeException("build 异常");
        }
        return t;
    }

    public T updateBuild(String user_name, T t) {
        try{
            Field[] fields = t.getClass().getDeclaredFields();
            for (Field field : fields){
                field.setAccessible(true);
                if(field.getName().equals("operUser")
                        || field.getName().equals("varOperUser")
                        || field.getName().equals("oper_user")){
                    field.set(t, user_name);
                }

                if(field.getName().equals("operTime")
                        || field.getName().equals("datOperDate")
                        || field.getName().equals("oper_time")){
                    field.set(t, DateUtils.formatDateTime(new Date()));
                }
            }
        } catch (Exception e){
            throw new RuntimeException("updateBuild 异常");
        }
        return t;
    }

    public Map<String, Object> build(User user) {
        Map<String, Object> data = MapUtil.getInstance();
        data.put("id", sequenceService.generator());
        data.put("create_user", user.getUser_name());
        data.put("create_time", new Date());
        data.put("oper_user", user.getUser_name());
        data.put("oper_time", new Date());
        return data;
    }

    public Map<String, Object> build(String user_name) {
        Map<String, Object> data = MapUtil.getInstance();
        data.put("id", sequenceService.generator());
        data.put("create_user", user_name);
        data.put("create_time", new Date());
        data.put("oper_user", user_name);
        data.put("oper_time", new Date());
        return data;
    }

    public Map<String, Object> buildMap(User user) {
        Map<String, Object> data = MapUtil.getInstance();
        data.put("id", sequenceService.generator());
        data.put("createUser", user.getUser_name());
        data.put("createTime", DateUtils.formatDateTime(new Date()));
        data.put("operUser", user.getUser_name());
        data.put("operTime", DateUtils.formatDateTime(new Date()));
        return data;
    }


}
