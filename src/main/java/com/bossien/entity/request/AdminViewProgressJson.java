package com.bossien.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 管理员查看学员学习进度
 * Created by Administrator on 2017/10/19.
 */
public class AdminViewProgressJson implements Serializable{

    @JsonProperty
    private String company_id;

    @JsonProperty
    private String project_id;

    @JsonProperty
    private String dept_name;

    @JsonProperty
    private Integer page_index = 1;

    @JsonProperty
    private Integer page_size = 10;

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
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
