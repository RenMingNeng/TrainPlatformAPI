package com.bossien.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 管理员查看项目详情
 * Created by Administrator on 2017/10/19.
 */
public class AdminProjectInfo implements Serializable{

    @JsonProperty
    private String show_type;// 1培训详情 2 练习详情 3考试详情

    @JsonProperty
    private String project_id;

    @JsonProperty
    private String train_role_id;

    @JsonProperty
    private String user_id;

    @JsonProperty
    private Integer page_index = 1;

    @JsonProperty
    private Integer page_size = 10;

    public String getShow_type() {
        return show_type;
    }

    public void setShow_type(String show_type) {
        this.show_type = show_type;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getTrain_role_id() {
        return train_role_id;
    }

    public void setTrain_role_id(String train_role_id) {
        this.train_role_id = train_role_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getPage_index() {
        return page_index;
    }

    public void setPage_index(Integer page_index) {
        this.page_index = page_index;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }
}
