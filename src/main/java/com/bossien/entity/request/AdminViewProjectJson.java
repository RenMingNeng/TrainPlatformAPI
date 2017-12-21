package com.bossien.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 学员查看项目课程
 * Created by Administrator on 2017/10/19.
 */
public class AdminViewProjectJson implements Serializable{

    @JsonProperty
    private String project_id;

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

}
