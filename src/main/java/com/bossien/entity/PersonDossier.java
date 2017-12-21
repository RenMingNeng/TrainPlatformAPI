package com.bossien.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by A on 2017/7/25.
 */
public class PersonDossier implements Serializable{

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("用户id")
    private String user_id;

    @ApiModelProperty("用户名称")
    private String user_name;

    @ApiModelProperty("角色编号")
    private String role_id;

    @ApiModelProperty("角色名称")
    private String role_name;

    @ApiModelProperty("部门名称")
    private String dept_name;

    @ApiModelProperty("公司编号")
    private String company_id;

    @ApiModelProperty("公司名称")
    private String company_name;

    @ApiModelProperty("已修学时")
    private long year_studytime;

    @ApiModelProperty("总学时")
    private long total_studytime;

    @ApiModelProperty("培训次数")
    private Integer train_count;
    private String create_user;
    private String create_time;
    private String oper_user;
    private String oper_time;

    public PersonDossier(){}

    public PersonDossier(String user_id, String user_name, String role_id, String role_name,
                         String dept_name, String company_id, String company_name, long year_studytime,
                         long total_studytime, Integer train_count){
        this.user_id = user_id;
        this.user_name = user_name;
        this.role_id = role_id;
        this.role_name = role_name;
        this.dept_name = dept_name;
        this.company_id = company_id;
        this.company_name = company_name;
        this.year_studytime = year_studytime;
        this.total_studytime = total_studytime;
        this.train_count = train_count;
    }

    public PersonDossier(String user_id, String company_id){
        this.user_id = user_id;
        this.company_id = company_id;
    }

    public PersonDossier(String user_id){
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public long getYear_studytime() {
        return year_studytime;
    }

    public void setYear_studytime(long year_studytime) {
        this.year_studytime = year_studytime;
    }

    public long getTotal_studytime() {
        return total_studytime;
    }

    public void setTotal_studytime(long total_studytime) {
        this.total_studytime = total_studytime;
    }

    public Integer getTrain_count() {
        return train_count;
    }

    public void setTrain_count(Integer train_count) {
        this.train_count = train_count;
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
