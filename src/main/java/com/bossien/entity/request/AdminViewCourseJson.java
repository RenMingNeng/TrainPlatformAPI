package com.bossien.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 管理员查看学员合格率，学习进度，成绩
 * Created by Administrator on 2017/10/19.
 */
public class AdminViewCourseJson implements Serializable{

    @JsonProperty
    private String project_id;

    @JsonProperty
    private Integer page_index = 1;

    @JsonProperty
    private Integer page_size = 10;

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
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
