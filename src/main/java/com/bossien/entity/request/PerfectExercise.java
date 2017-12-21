package com.bossien.entity.request;

import java.io.Serializable;

/**
 * 专项练习接口入参
 * Created by Administrator on 2017/10/18.
 */
public class PerfectExercise implements Serializable {

     private String user_id;
     private String project_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

}
