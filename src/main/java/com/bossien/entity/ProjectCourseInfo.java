package com.bossien.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 课程信息
 */

@ApiModel(description = "反馈实体对象")
@TableName("course_info")
public class ProjectCourseInfo implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("id")
    private String id;

    /** 项目id */
    @ApiModelProperty("项目id")
    private String project_id;

    /** 课程id */
    @ApiModelProperty("课程id")
    private String course_id;

    /** 课程名称 */
    @ApiModelProperty("课程名称")
    private String course_name;

    /** 课时 */
    @ApiModelProperty("课时")
    private Integer class_hour;

    /** 用户id */
    @ApiModelProperty("用户id")
    private String user_id;

    /** 应修学时 */
    @ApiModelProperty("应修学时")
    private Long requirement_studytime;

    /** 已修学时 */
    @ApiModelProperty("已修学时")
    private Long total_studytime;

    /** 答题学时 */
    @ApiModelProperty("答题学时")
    private Long answer_studytime;

    /** 培训学时 */
    @ApiModelProperty("培训学时")
    private Long train_studytime;

    /** 总题量 */
    @ApiModelProperty("总题量")
    private Integer total_question;

    /** 已答题量 */
    @ApiModelProperty("已答题量")
    private Integer yet_answered;

    /** 答对题量 */
    @ApiModelProperty("答对题量")
    private Integer correct_answered;

    /** 总答正确率 */
    @ApiModelProperty("总答正确率")
    private Double correct_rate;

    /** 完成状态 */
    @ApiModelProperty("完成状态")
    private String finish_status;

    /** 课程类型名称 */
    @ApiModelProperty("课程类型名称")
    private String couse_type_name;

    /** 创建用户 */
    @ApiModelProperty("创建用户")
    private String create_user;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private String create_time;

    /** 操作用户 */
    @ApiModelProperty("操作用户")
    private String oper_user;

    /** 操作时间 */
    @ApiModelProperty("操作时间")
    private String oper_time;

    public ProjectCourseInfo(){}

    public ProjectCourseInfo(String project_id, String course_id, String user_id){
        this.project_id = project_id;
        this.course_id = course_id;
        this.user_id = user_id;
    }

    public ProjectCourseInfo(String id, String project_id, String course_id, String course_name, Integer class_hour,
                             String user_id, Long requirement_studytime, Long total_studytime, Long answer_studytime,
                             Long train_studytime, Integer total_question, Integer yet_answered, Integer correct_answered,
                             Double correct_rate, String finish_status, String create_user, String create_time,
                             String oper_user, String oper_time) {
        this.id = id;
        this.project_id = project_id;
        this.course_id = course_id;
        this.course_name = course_name;
        this.class_hour = class_hour;
        this.user_id = user_id;
        this.requirement_studytime = requirement_studytime;
        this.total_studytime = total_studytime;
        this.answer_studytime = answer_studytime;
        this.train_studytime = train_studytime;
        this.total_question = total_question;
        this.yet_answered = yet_answered;
        this.correct_answered = correct_answered;
        this.correct_rate = correct_rate;
        this.finish_status = finish_status;
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

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Integer getClass_hour() {
        return class_hour;
    }

    public void setClass_hour(Integer class_hour) {
        this.class_hour = class_hour;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getFinish_status() {
        return finish_status;
    }

    public void setFinish_status(String finish_status) {
        this.finish_status = finish_status;
    }

    public String getCouse_type_name() {
        return couse_type_name;
    }

    public void setCouse_type_name(String couse_type_name) {
        this.couse_type_name = couse_type_name;
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
