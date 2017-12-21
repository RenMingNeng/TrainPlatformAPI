package com.bossien.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * project_user实体
 * Created by zhaoli on 2017/7/25.
 */
public class ProjectUser implements Serializable{

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("项目id")
    private String project_id;

    @ApiModelProperty("用户id")
    private String user_id;

    @ApiModelProperty("用户名称")
    private String user_name;

    @ApiModelProperty("角色编号")
    private String role_id;

    @ApiModelProperty("角色名称")
    private String role_name;

    @ApiModelProperty("部门名称")
    private String department_name;
    private String create_user;
    private String create_time;
    private String oper_user;
    private String oper_time;

    public ProjectUser(){}

    public ProjectUser(String project_id, String user_id){
        this.project_id = project_id;
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
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
