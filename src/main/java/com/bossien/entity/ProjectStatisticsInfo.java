package com.bossien.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-07-26.
 */
public class ProjectStatisticsInfo implements Serializable {
    @ApiModelProperty("id")
    private String id;      //主键

    @ApiModelProperty("项目编号")
    private String project_id;      //项目序号

    @ApiModelProperty("项目名称")
    private String project_name;     //项目名称

    @ApiModelProperty("项目开始时间")
    private String project_start_time;      //项目开始时间

    @ApiModelProperty("项目结束时间")
    private String project_end_time;     //项目结束时间

    @ApiModelProperty("用户id")
    private String user_id;     //用户序号

    @ApiModelProperty("用户名称")
    private String user_name;     //用户名称

    @ApiModelProperty("角色编号")
    private String role_id;     //角色序号

    @ApiModelProperty("角色名称")
    private String role_name;       //角色名称

    @ApiModelProperty("部门名称")
    private String dept_name;       //单位名称

    @ApiModelProperty("应修学时(单位秒)")
    private Long requirement_studytime;     //应修学时(单位秒）

    @ApiModelProperty("已修总学时(单位秒)")
    private Long total_studytime;       //已修总学时(单位秒）

    @ApiModelProperty("答题学时(单位秒)")
    private Long answer_studytime;      //答题学时(单位秒）

    @ApiModelProperty("培训学时(单位秒)")
    private Long train_studytime;       //培训学时(单位秒）

    @ApiModelProperty("总题量")
    private Integer total_question;     //总题量

    @ApiModelProperty("已答题量")
    private Integer yet_answered;       //已答题量

    @ApiModelProperty("答对题量")
    private Integer correct_answered;       //答对题量

    @ApiModelProperty("答题正确率")
    private Double correct_rate;        //答题正确率

    @ApiModelProperty("培训完成状态")
    private String train_status;    //培训完成状态

    @ApiModelProperty("试卷编号")
    private String exam_no;     //试卷编号

    @ApiModelProperty("用户考试时间")
    private String exam_time_info;      //用户考试时间：开始时间+结束时间

    @ApiModelProperty("考试成绩")
    private String exam_score;      //考试成绩

    @ApiModelProperty("考试状态")
    private String exam_status;     //考试状态:1未考试2合格3不合格
    private String create_user;     //创建用户
    private String create_time;     //创建日期
    private String oper_user;       //操作用户
    private String oper_time;       //操作日期

    public ProjectStatisticsInfo() {

    }

    public ProjectStatisticsInfo(String project_id, String user_id) {
        this.project_id = project_id;
        this.user_id = user_id;
    }

    public ProjectStatisticsInfo(String id, String project_id, String project_start_time, String project_end_time,
                                 String user_id, String role_id, String role_name, String dept_name,
                                 Long requirement_studytime, Long total_studytime, Long answer_studytime,
                                 Long train_studytime, Integer total_question, Integer yet_answered,
                                 Integer correct_answered, Double correct_rate, String train_status, String exam_no, String exam_time_info,
                                 String exam_score, String exam_status, String create_user, String create_time,
                                 String oper_user, String oper_time) {
        this.id = id;
        this.project_id = project_id;
        this.project_start_time = project_start_time;
        this.project_end_time = project_end_time;
        this.user_id = user_id;
        this.role_id = role_id;
        this.role_name = role_name;
        this.dept_name = dept_name;
        this.requirement_studytime = requirement_studytime;
        this.total_studytime = total_studytime;
        this.answer_studytime = answer_studytime;
        this.train_studytime = train_studytime;
        this.total_question = total_question;
        this.yet_answered = yet_answered;
        this.correct_answered = correct_answered;
        this.correct_rate = correct_rate;
        this.train_status = train_status;
        this.exam_no = exam_no;
        this.exam_time_info = exam_time_info;
        this.exam_score = exam_score;
        this.exam_status = exam_status;
        this.create_user = create_user;
        this.create_time = create_time;
        this.oper_user = oper_user;
        this.oper_time = oper_time;
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

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
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

    public Long getRequirement_studytime() {
        return requirement_studytime;
    }

    public void setRequirement_studytime(Long requirement_studytime) {
        this.requirement_studytime = requirement_studytime;
    }

    public Long getTotal_studytime() {
        return total_studytime;
    }

    public void setTotal_studytime(Long total_studytime) {
        this.total_studytime = total_studytime;
    }

    public Long getAnswer_studytime() {
        return answer_studytime;
    }

    public void setAnswer_studytime(Long answer_studytime) {
        this.answer_studytime = answer_studytime;
    }

    public Long getTrain_studytime() {
        return train_studytime;
    }

    public void setTrain_studytime(Long train_studytime) {
        this.train_studytime = train_studytime;
    }

    public Integer getTotal_question() {
        return total_question;
    }

    public void setTotal_question(Integer total_question) {
        this.total_question = total_question;
    }

    public Integer getYet_answered() {
        return yet_answered;
    }

    public void setYet_answered(Integer yet_answered) {
        this.yet_answered = yet_answered;
    }

    public Integer getCorrect_answered() {
        return correct_answered;
    }

    public void setCorrect_answered(Integer correct_answered) {
        this.correct_answered = correct_answered;
    }

    public Double getCorrect_rate() {
        return correct_rate;
    }

    public void setCorrect_rate(Double correct_rate) {
        this.correct_rate = correct_rate;
    }

    public String getTrain_status() {
        return train_status;
    }

    public void setTrain_status(String train_status) {
        this.train_status = train_status;
    }

    public String getExam_no() {
        return exam_no;
    }

    public void setExam_no(String exam_no) {
        this.exam_no = exam_no;
    }

    public String getExam_time_info() {
        return exam_time_info;
    }

    public void setExam_time_info(String exam_time_info) {
        this.exam_time_info = exam_time_info;
    }

    public String getExam_score() {
        return exam_score;
    }

    public void setExam_score(String exam_score) {
        this.exam_score = exam_score;
    }

    public String getExam_status() {
        return exam_status;
    }

    public void setExam_status(String exam_status) {
        this.exam_status = exam_status;
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
