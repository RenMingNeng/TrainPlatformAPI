package com.bossien.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by A on 2017/10/18.
 */
@ApiModel(description = "创建考卷参数")
public class ViewStudentProjectsJson implements Serializable {
    @ApiModelProperty("公司编号")
    String company_id;

    @ApiModelProperty("用户编号")
    String user_id;

    @ApiModelProperty("项目状态")
    String project_status;

    @ApiModelProperty("当前页")
    Integer page_index;

    @ApiModelProperty("每页显示条数")
    Integer page_size;

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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
}
