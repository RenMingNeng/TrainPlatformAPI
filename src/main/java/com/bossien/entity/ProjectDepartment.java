package com.bossien.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/19.
 */
public class ProjectDepartment implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("id")
    private String id;

    /** 所属单位编号 */
    @ApiModelProperty("company_id")
    private String company_id;

    /** 项目编号 */
    @ApiModelProperty("project_id")
    private String project_id;

    /** 部门编号 */
    @ApiModelProperty("dept_id")
    private String dept_id;

    /** 部门名称 */
    @ApiModelProperty("dept_name")
    private String dept_name;

    private String create_user;
    private String create_time;
    private String oper_user;
    private String oper_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getOper_user() {
        return oper_user;
    }

    public void setOper_user(String oper_user) {
        this.oper_user = oper_user;
    }

    public String getOper_time() {
        return oper_time;
    }

    public void setOper_time(String oper_time) {
        this.oper_time = oper_time;
    }
}
