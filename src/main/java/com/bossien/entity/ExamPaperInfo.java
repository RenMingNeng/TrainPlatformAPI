package com.bossien.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 考试试卷信息表
 */
@TableName("exam_paper_info")
@ApiModel(description = "反馈实体对象")
public class ExamPaperInfo {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 考试编号
     */
    @ApiModelProperty("考试编号")
    private String exam_no;

    /**
     * 项目id
     */
    @ApiModelProperty("项目id")
    private String project_id;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private String user_id;

    /**
     * 角色id
     */
    @ApiModelProperty("角色id")
    private String role_id;

    /**
     * 考试类型1：模拟考试2：正式考试
     */
    @ApiModelProperty("考试类型1：模拟考试2：正式考试")
    private String exam_type;

    /**
     * 单选题分值
     */
    @ApiModelProperty("单选题分值")
    private String single_score;

    /**
     * 多选题分值
     */
    @ApiModelProperty("多选题分值")
    private String many_score;

    /**
     * 判断题分值
     */
    @ApiModelProperty("判断题分值")
    private String judge_score;

    /**
     * 填空题分值
     */
    @ApiModelProperty("填空题分值")
    private String fillout_score;

    /**
     * 问答题分值
     */
    @ApiModelProperty("问答分值")
    private String ques_ans_score;

    /**
     * 合格分
     */
    @ApiModelProperty("合格分")
    private String pass_score;

    /**
     * 总分
     */
    @ApiModelProperty("总分")
    private String total_score;

    /**
     * 考试时长
     */
    @ApiModelProperty("考试时长")
    private String exam_duration;

    /**
     * 考试状态：1未考试2考试
     */
    @ApiModelProperty("考试状态：1未考试2考试")
    private String exam_status;

    /**
     * 学时要求
     */
    @ApiModelProperty("学时要求")
    private String necessary_hour;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private String create_time;


   public ExamPaperInfo(){}
   public ExamPaperInfo(String exam_no){
       this.exam_no = exam_no;
   }

    public ExamPaperInfo(String project_id, String user_id, String exam_status){
        this.project_id = project_id;
        this.user_id = user_id;
        this.exam_status = exam_status;
    }

    public ExamPaperInfo(String project_id, String user_id, String exam_type, String exam_status){
        this.project_id = project_id;
        this.user_id = user_id;
        this.exam_type = exam_type;
        this.exam_status = exam_status;
    }

    public String getExam_no() {
        return exam_no;
    }

    public void setExam_no(String exam_no) {
        this.exam_no = exam_no;
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

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getExam_type() {
        return exam_type;
    }

    public void setExam_type(String exam_type) {
        this.exam_type = exam_type;
    }

    public String getSingle_score() {
        return single_score;
    }

    public void setSingle_score(String single_score) {
        this.single_score = single_score;
    }

    public String getMany_score() {
        return many_score;
    }

    public void setMany_score(String many_score) {
        this.many_score = many_score;
    }

    public String getJudge_score() {
        return judge_score;
    }

    public void setJudge_score(String judge_score) {
        this.judge_score = judge_score;
    }

    public String getFillout_score() {
        return fillout_score;
    }

    public void setFillout_score(String fillout_score) {
        this.fillout_score = fillout_score;
    }

    public String getQues_ans_score() {
        return ques_ans_score;
    }

    public void setQues_ans_score(String ques_ans_score) {
        this.ques_ans_score = ques_ans_score;
    }

    public String getPass_score() {
        return pass_score;
    }

    public void setPass_score(String pass_score) {
        this.pass_score = pass_score;
    }

    public String getTotal_score() {
        return total_score;
    }

    public void setTotal_score(String total_score) {
        this.total_score = total_score;
    }

    public String getExam_duration() {
        return exam_duration;
    }

    public void setExam_duration(String exam_duration) {
        this.exam_duration = exam_duration;
    }

    public String getExam_status() {
        return exam_status;
    }

    public void setExam_status(String exam_status) {
        this.exam_status = exam_status;
    }

    public String getNecessary_hour() {
        return necessary_hour;
    }

    public void setNecessary_hour(String necessary_hour) {
        this.necessary_hour = necessary_hour;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
