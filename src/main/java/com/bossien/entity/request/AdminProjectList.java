package com.bossien.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 管理员下项目列表
 */
public class AdminProjectList implements Serializable{

    @JsonProperty
    private String company_id;

    @JsonProperty
    private String project_status;//1未发布 2未开始 3进行中 4已结束

    @JsonProperty
    private List<String> projectIds;

    @JsonProperty
    private Integer page_index=1;

    @JsonProperty
    private Integer page_size=10;

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getProject_status() {
        return project_status;
    }

    public void setProject_status(String project_status) {
        this.project_status = project_status;
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

    public List<String> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(List<String> projectIds) {
        this.projectIds = projectIds;
    }
}
