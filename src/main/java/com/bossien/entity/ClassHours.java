package com.bossien.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ClassHours implements Serializable {

    @ApiModelProperty("项目编号")
    private String project_id;      // 项目id

    @ApiModelProperty("课程编号")
    private String course_id;       // 课程id

    @ApiModelProperty("用户id")
    private String user_id;         // 用户id

    @ApiModelProperty("来源")
    private String source;          // 学时来源-1：学习、1：练习答题（正确）、2：练习答题（错误）、3：考试答题（正确）、4：考试答题（错误）',

    @ApiModelProperty("累计学时")
    private Long study_time;      // 累加学时

    private String create_user;     // 创建用户
    private String create_time;     // 创建日期

    public ClassHours() {

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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getStudy_time() {
        return study_time;
    }

    public void setStudy_time(Long study_time) {
        this.study_time = study_time;
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

    public ClassHours(String project_id, String course_id, String user_id, String source, Long study_time, String create_user, String create_time) {
        this.project_id = project_id;
        this.course_id = course_id;
        this.user_id = user_id;
        this.source = source;
        this.study_time = study_time;
        this.create_user = create_user;
        this.create_time = create_time;
    }
}
