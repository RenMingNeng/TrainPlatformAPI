package com.bossien.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * 试卷组卷策略
 */
@TableName("project_basic")
@ApiModel(description = "反馈实体对象")

public class ProjectBasic implements Serializable {
    private String id;
    private String project_name;
    private String subject_id;
    private String project_type;
    private String train_period;
    private String project_mode;
    private String project_status;
    private String project_train_info;
    private String project_exercise_info;
    private String project_exam_info;
    private String project_open;
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

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public String getTrain_period() {
        return train_period;
    }

    public void setTrain_period(String train_period) {
        this.train_period = train_period;
    }

    public String getProject_mode() {
        return project_mode;
    }

    public void setProject_mode(String project_mode) {
        this.project_mode = project_mode;
    }

    public String getProject_status() {
        return project_status;
    }

    public void setProject_status(String project_status) {
        this.project_status = project_status;
    }

    public String getProject_train_info() {
        return project_train_info;
    }

    public void setProject_train_info(String project_train_info) {
        this.project_train_info = project_train_info;
    }

    public String getProject_exercise_info() {
        return project_exercise_info;
    }

    public void setProject_exercise_info(String project_exercise_info) {
        this.project_exercise_info = project_exercise_info;
    }

    public String getProject_exam_info() {
        return project_exam_info;
    }

    public void setProject_exam_info(String project_exam_info) {
        this.project_exam_info = project_exam_info;
    }

    public String getProject_open() {
        return project_open;
    }

    public void setProject_open(String project_open) {
        this.project_open = project_open;
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
