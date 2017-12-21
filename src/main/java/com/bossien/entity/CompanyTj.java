package com.bossien.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


@ApiModel(description = "反馈实体对象")
@TableName("company_tj")
public class CompanyTj implements Serializable {
        @ApiModelProperty("单位编号")
        private String  company_id;               //公司id

        @ApiModelProperty("单位名称")
        private String  company_name;            //公司名称

        @ApiModelProperty("用户人数")
        private String  count_user;               //学员人数

        @ApiModelProperty("累计学时")
        private String  total_class_hour;          //累计学时

        private String  count_project;            //项目数量
        private String  count_train;              //参与培训人次
        private String  count_train_complete_yes;   //完成培训人次
        private String  count_exam;               //参与考试人次
        private String  count_exam_pass_yes;        //考试合格人次
        private String  percent_train_complete;    //培训完成率
        private String  average_year_class_hour;          //培训人数
        private String  count_train_user;              //课程数量
        private String  count_course;               //题库数量
        private String  count_question;            //累计学时
        private String  average_person_class_hour;       //年度累计学时
        private String  total_year_class_hour;    //年度平均学时
        private String  createTime;             //创建时间
        private String  createUser;             //操作用户
        private String  operTime;               //操作时间
        private String  operUser;               //操作用户

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

    public String getCount_user() {
        return count_user;
    }

    public void setCount_user(String count_user) {
        this.count_user = count_user;
    }

    public String getTotal_class_hour() {
        return total_class_hour;
    }

    public void setTotal_class_hour(String total_class_hour) {
        this.total_class_hour = total_class_hour;
    }

    public String getCount_project() {
        return count_project;
    }

    public void setCount_project(String count_project) {
        this.count_project = count_project;
    }

    public String getCount_train() {
        return count_train;
    }

    public void setCount_train(String count_train) {
        this.count_train = count_train;
    }

    public String getCount_train_complete_yes() {
        return count_train_complete_yes;
    }

    public void setCount_train_complete_yes(String count_train_complete_yes) {
        this.count_train_complete_yes = count_train_complete_yes;
    }

    public String getCount_exam() {
        return count_exam;
    }

    public void setCount_exam(String count_exam) {
        this.count_exam = count_exam;
    }

    public String getCount_exam_pass_yes() {
        return count_exam_pass_yes;
    }

    public void setCount_exam_pass_yes(String count_exam_pass_yes) {
        this.count_exam_pass_yes = count_exam_pass_yes;
    }

    public String getPercent_train_complete() {
        return percent_train_complete;
    }

    public void setPercent_train_complete(String percent_train_complete) {
        this.percent_train_complete = percent_train_complete;
    }

    public String getAverage_year_class_hour() {
        return average_year_class_hour;
    }

    public void setAverage_year_class_hour(String average_year_class_hour) {
        this.average_year_class_hour = average_year_class_hour;
    }

    public String getCount_train_user() {
        return count_train_user;
    }

    public void setCount_train_user(String count_train_user) {
        this.count_train_user = count_train_user;
    }

    public String getCount_course() {
        return count_course;
    }

    public void setCount_course(String count_course) {
        this.count_course = count_course;
    }

    public String getCount_question() {
        return count_question;
    }

    public void setCount_question(String count_question) {
        this.count_question = count_question;
    }

    public String getAverage_person_class_hour() {
        return average_person_class_hour;
    }

    public void setAverage_person_class_hour(String average_person_class_hour) {
        this.average_person_class_hour = average_person_class_hour;
    }

    public String getTotal_year_class_hour() {
        return total_year_class_hour;
    }

    public void setTotal_year_class_hour(String total_year_class_hour) {
        this.total_year_class_hour = total_year_class_hour;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getOperTime() {
        return operTime;
    }

    public void setOperTime(String operTime) {
        this.operTime = operTime;
    }

    public String getOperUser() {
        return operUser;
    }

    public void setOperUser(String operUser) {
        this.operUser = operUser;
    }
}
