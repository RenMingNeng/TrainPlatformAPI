package com.bossien.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/26.
 */
public class ProjectInfo implements Serializable {

    /** 主键 */
    @ApiModelProperty("id")
    private String id;

    /** 主题名称 */
    @ApiModelProperty("subject_name")
    private String subject_name;

    /** 项目名称 */
    @ApiModelProperty("project_name")
    private String project_name;

    /** 角色名称 */
    @ApiModelProperty("role_name")
    private String role_name;

    /** 项目开始时间 */
    @ApiModelProperty("project_start_time")
    private String project_start_time;

    /** 项目结束时间 */
    @ApiModelProperty("project_end_time")
    private String project_end_time;

    /** 培训时间 */
    @ApiModelProperty("project_train_time")
    private String project_train_time;

    /** 练习时间 */
    @ApiModelProperty("project_exercise_time")
    private String project_exercise_time;

    /** 考试时间 */
    @ApiModelProperty("project_exam_time")
    private String project_exam_time;

    /** 培训周期 */
    @ApiModelProperty("intTrainPeriod")
    private Integer intTrainPeriod;

    /** 考试次数 */
    @ApiModelProperty("intRetestTime")
    private Integer intRetestTime;

    /** 人数 */
    @ApiModelProperty("person_count")
    private Integer person_count;

    /** 标识公开与私有 */
    @ApiModelProperty("project_mode")
    private String project_mode;

    /** 项目类型 */
    @ApiModelProperty("project_type")
    private String project_type;

    /** 项目状态 */
    @ApiModelProperty("project_status")
    private String project_status;

    /** 创建时间 */
    @ApiModelProperty("create_time")
    private String create_time;

    /** 创建人 */
    @ApiModelProperty("create_user")
    private String create_user;

    /** 操作时间 */
    @ApiModelProperty("oper_time")
    private String oper_time;

    /** 操作人 */
    @ApiModelProperty("oper_user")
    private String oper_user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getProject_start_time() {
        return project_start_time;
    }

    public void setProject_start_time(String project_start_time) {
        this.project_start_time = project_start_time;
    }

    public String getProject_end_time() {
        return project_end_time;
    }

    public void setProject_end_time(String project_end_time) {
        this.project_end_time = project_end_time;
    }

    public String getProject_train_time() {
        return project_train_time;
    }

    public void setProject_train_time(String project_train_time) {
        this.project_train_time = project_train_time;
    }

    public String getProject_exercise_time() {
        return project_exercise_time;
    }

    public void setProject_exercise_time(String project_exercise_time) {
        this.project_exercise_time = project_exercise_time;
    }

    public String getProject_exam_time() {
        return project_exam_time;
    }

    public void setProject_exam_time(String project_exam_time) {
        this.project_exam_time = project_exam_time;
    }

    public Integer getIntTrainPeriod() {
        return intTrainPeriod;
    }

    public void setIntTrainPeriod(Integer intTrainPeriod) {
        this.intTrainPeriod = intTrainPeriod;
    }

    public Integer getIntRetestTime() {
        return intRetestTime;
    }

    public void setIntRetestTime(Integer intRetestTime) {
        this.intRetestTime = intRetestTime;
    }

    public Integer getPerson_count() {
        return person_count;
    }

    public void setPerson_count(Integer person_count) {
        this.person_count = person_count;
    }

    public String getProject_mode() {
        return project_mode;
    }

    public void setProject_mode(String project_mode) {
        this.project_mode = project_mode;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public String getProject_status() {
        return project_status;
    }

    public void setProject_status(String project_status) {
        this.project_status = project_status;
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

    public String getOper_user() {
        return oper_user;
    }

    public void setOper_user(String oper_user) {
        this.oper_user = oper_user;
    }

    // 项目状态 1：未发布、2：未开始、3：进行中、4：已结束
    public enum Project_status {

        TYPE_1("未发布", "1"), TYPE_2("未开始", "2"), TYPE_3("进行中", "3"), TYPE_4("已结束", "4");
        // 枚举中文
        private String name;
        // 枚举值
        private String value;

        // 枚举翻译
        public static Project_status getEnum(String value) {
            Project_status[] is = Project_status.values();
            for (Project_status i : is) {
                if (!value.equals(i.getValue())) {
                    continue;
                }
                return i;
            }
            return null;
        }

        // 构造
        Project_status(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
