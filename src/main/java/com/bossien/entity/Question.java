package com.bossien.entity;

import java.io.Serializable;

/**
 * Created by A on 2017/7/31.
 */
public class Question implements Serializable {
    private static final long serialVersionUID = 6227595535045711013L;
    private String int_id;
    private String var_no; //题号：需要提供编号规则
    private String var_title;  //题目简称chrCategory
    private String var_content;  //题目内容，格式：{"title":"","titleImgs":[{"fileId":"","fileName":"","filePath":""}],"options":
    // [{"item":"","itemDesc":"","fileInfo":{"fileId":"","fileName":"","filePath":""}}]}
    private Content content;
    private String chr_category;  //试题类型:1.文字题、2.多媒体题、3.图片题
    private String chr_type;     //题目类型：01.单选题 02.多选题 03.判断题 04.填空题 05.简答题 06.论述题 07.分析题
    private String int_difficult;    //试题难度系数：1到10
    private String chr_valid;        //是否有效：1-有效 2-无效
    private String var_answer;       //用于记录单选、多选题、判断题选择型答案
    private String var_answer_desc;      //用于记录填空、简答、分析文字型答案
    private String var_source;       //试题来源
    private String var_analysis;     //试题解析,格式：{"content":"","images":{}}
    private String var_exam_point;       //试题考点
    private String var_create_user;
    private String dat_create_date;
    private String var_oper_user;
    private String dat_oper_date;
    private String int_important;
    private String var_industry;

    public String getInt_id() {
        return int_id;
    }

    public void setInt_id(String int_id) {
        this.int_id = int_id;
    }

    public String getVar_no() {
        return var_no;
    }

    public void setVar_no(String var_no) {
        this.var_no = var_no;
    }

    public String getVar_title() {
        return var_title;
    }

    public void setVar_title(String var_title) {
        this.var_title = var_title;
    }

    public String getVar_content() {
        return var_content;
    }

    public void setVar_content(String var_content) {
        this.var_content = var_content;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getChr_category() {
        return chr_category;
    }

    public void setChr_category(String chr_category) {
        this.chr_category = chr_category;
    }

    public String getChr_type() {
        return chr_type;
    }

    public void setChr_type(String chr_type) {
        this.chr_type = chr_type;
    }

    public String getInt_difficult() {
        return int_difficult;
    }

    public void setInt_difficult(String int_difficult) {
        this.int_difficult = int_difficult;
    }

    public String getChr_valid() {
        return chr_valid;
    }

    public void setChr_valid(String chr_valid) {
        this.chr_valid = chr_valid;
    }

    public String getVar_answer() {
        return var_answer;
    }

    public void setVar_answer(String var_answer) {
        this.var_answer = var_answer;
    }

    public String getVar_answer_desc() {
        return var_answer_desc;
    }

    public void setVar_answer_desc(String var_answer_desc) {
        this.var_answer_desc = var_answer_desc;
    }

    public String getVar_source() {
        return var_source;
    }

    public void setVar_source(String var_source) {
        this.var_source = var_source;
    }

    public String getVar_analysis() {
        return var_analysis;
    }

    public void setVar_analysis(String var_analysis) {
        this.var_analysis = var_analysis;
    }

    public String getVar_exam_point() {
        return var_exam_point;
    }

    public void setVar_exam_point(String var_exam_point) {
        this.var_exam_point = var_exam_point;
    }

    public String getVar_create_user() {
        return var_create_user;
    }

    public void setVar_create_user(String var_create_user) {
        this.var_create_user = var_create_user;
    }

    public String getDat_create_date() {
        return dat_create_date;
    }

    public void setDat_create_date(String dat_create_date) {
        this.dat_create_date = dat_create_date;
    }

    public String getVar_oper_user() {
        return var_oper_user;
    }

    public void setVar_oper_user(String var_oper_user) {
        this.var_oper_user = var_oper_user;
    }

    public String getDat_oper_date() {
        return dat_oper_date;
    }

    public void setDat_oper_date(String dat_oper_date) {
        this.dat_oper_date = dat_oper_date;
    }

    public String getInt_important() {
        return int_important;
    }

    public void setInt_important(String int_important) {
        this.int_important = int_important;
    }

    public String getVar_industry() {
        return var_industry;
    }

    public void setVar_industry(String var_industry) {
        this.var_industry = var_industry;
    }
}
