package com.bossien.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 项目课程表
 * Created by Administrator on 2017/7/25.
 */
public class ProjectCourse implements Serializable {
    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("项目编号")
    private String project_id;        //项目编号

    @ApiModelProperty("课程id")
    private String course_id;          //课程Id

    @ApiModelProperty("课程编号")
    private String course_no;          //课程编号

    @ApiModelProperty("答题学时")
    private String course_name;        //课程名称

    @ApiModelProperty("角色id")
    private String role_id;            //角色id

    @ApiModelProperty("角色名称")
    private String role_name;          //角色名称

    @ApiModelProperty("学时要求")
    private Integer requirement;       //学时要求

    @ApiModelProperty("课时")
    private Integer class_hour;       //课时

    @ApiModelProperty("题量")
    private Integer question_count;       //题量

    @ApiModelProperty("必选题量")
    private Integer select_count;       //必选题量
    private String create_time;          //创建时间
    private String create_user;          //操作用户
    private String oper_time;         //操作时间
    private String oper_user;         //操作时间

    public String getOper_user() {
        return oper_user;
    }

    public void setOper_user(String oper_user) {
        this.oper_user = oper_user;
    }

    public ProjectCourse(){}

    public ProjectCourse(String project_id, String role_id){
        this.project_id = project_id;
        this.role_id = role_id;
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

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_no() {
        return course_no;
    }

    public void setCourse_no(String course_no) {
        this.course_no = course_no;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
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

    public Integer getRequirement() {
        return requirement;
    }

    public void setRequirement(Integer requirement) {
        this.requirement = requirement;
    }

    public Integer getClass_hour() {
        return class_hour;
    }

    public void setClass_hour(Integer class_hour) {
        this.class_hour = class_hour;
    }

    public Integer getQuestion_count() {
        return question_count;
    }

    public void setQuestion_count(Integer question_count) {
        this.question_count = question_count;
    }

    public Integer getSelect_count() {
        return select_count;
    }

    public void setSelect_count(Integer select_count) {
        this.select_count = select_count;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getOper_time() {
        return oper_time;
    }

    public void setOper_time(String oper_time) {
        this.oper_time = oper_time;
    }
}
