package com.bossien.entity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 试卷组卷策略
 */
@TableName("exam_strategy")
@ApiModel(description = "反馈实体对象")

public class ExamStrategy {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("id")
    private String id;

    /**
     * 项目id
     */
    @ApiModelProperty("项目id")
    private String project_id;

    /**
     * 角色id
     */
    @ApiModelProperty("角色id")
    private String role_id;

    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    private String role_name;

    /**
     * 总分
     */
    @ApiModelProperty("总分")
    private String total_score;

    /**
     * 学时要求
     */
    @ApiModelProperty("学时要求")
    private Integer necessary_hour;

    /**
     * 考试时长
     */
    @ApiModelProperty("考试时长")
    private String exam_duration;

    /**
     * 合格分
     */
    @ApiModelProperty("合格分")
    private String pass_score;

    /**
     * 单选题数量
     */
    @ApiModelProperty("单选题数量")
    private Integer single_count;

    /**
     * 单选题分值
     */
    @ApiModelProperty("单选题分值")
    private Integer single_score;

    /**
     * 单选题总题量
     */
    @ApiModelProperty("单选题总题量")
    private Integer single_all_count;

    /**
     * 多选题数量
     */
    @ApiModelProperty("多选题数量")
    private Integer many_count;

    /**
     * 多选题分值
     */
    @ApiModelProperty("多选题分值")
    private Integer many_score;

    /**
     * 多选题总题量
     */
    @ApiModelProperty("多选题总题量")
    private Integer many_all_count;

    /**
     * 判断题数量
     */
    @ApiModelProperty("判断题数量")
    private Integer judge_count;

    /**
     * 判断题分值
     */
    @ApiModelProperty("判断题分值")
    private Integer judge_score;

    /**
     * 判断题总题量
     */
    @ApiModelProperty("判断题总题量")
    private Integer judge_all_count;

    /**
     * 填空题数量
     */
    @ApiModelProperty("填空题数量")
    private Integer fillout_count;

    /**
     * 填空题分值
     */
    @ApiModelProperty("填空题分值")
    private Integer fillout_score;

    /**
     * 问答题数量
     */
    @ApiModelProperty("问答题数量")
    private Integer ques_ans_count;

    /**
     * 问答题分值
     */
    @ApiModelProperty("问答题分值")
    private Integer ques_ans_score;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String create_user;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date create_time;

    /**
     * 操作人
     */
    @ApiModelProperty("操作人")
    private String oper_user;

    /**
     * 操作时间
     */
    @ApiModelProperty("操作时间")
    private Date oper_time;


    public ExamStrategy(){}
    public ExamStrategy(String project_id){
        this.project_id = project_id;
    }
    public ExamStrategy(String project_id,String role_id){
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

    public String getTotal_score() {
        return total_score;
    }

    public void setTotal_score(String total_score) {
        this.total_score = total_score;
    }

    public Integer getNecessary_hour() {
        return necessary_hour;
    }

    public void setNecessary_hour(Integer necessary_hour) {
        this.necessary_hour = necessary_hour;
    }

    public String getExam_duration() {
        return exam_duration;
    }

    public void setExam_duration(String exam_duration) {
        this.exam_duration = exam_duration;
    }

    public String getPass_score() {
        return pass_score;
    }

    public void setPass_score(String pass_score) {
        this.pass_score = pass_score;
    }

    public Integer getSingle_count() {
        return single_count;
    }

    public void setSingle_count(Integer single_count) {
        this.single_count = single_count;
    }

    public Integer getSingle_score() {
        return single_score;
    }

    public void setSingle_score(Integer single_score) {
        this.single_score = single_score;
    }

    public Integer getSingle_all_count() {
        return single_all_count;
    }

    public void setSingle_all_count(Integer single_all_count) {
        this.single_all_count = single_all_count;
    }

    public Integer getMany_count() {
        return many_count;
    }

    public void setMany_count(Integer many_count) {
        this.many_count = many_count;
    }

    public Integer getMany_score() {
        return many_score;
    }

    public void setMany_score(Integer many_score) {
        this.many_score = many_score;
    }

    public Integer getMany_all_count() {
        return many_all_count;
    }

    public void setMany_all_count(Integer many_all_count) {
        this.many_all_count = many_all_count;
    }

    public Integer getJudge_count() {
        return judge_count;
    }

    public void setJudge_count(Integer judge_count) {
        this.judge_count = judge_count;
    }

    public Integer getJudge_score() {
        return judge_score;
    }

    public void setJudge_score(Integer judge_score) {
        this.judge_score = judge_score;
    }

    public Integer getJudge_all_count() {
        return judge_all_count;
    }

    public void setJudge_all_count(Integer judge_all_count) {
        this.judge_all_count = judge_all_count;
    }

    public Integer getFillout_count() {
        return fillout_count;
    }

    public void setFillout_count(Integer fillout_count) {
        this.fillout_count = fillout_count;
    }

    public Integer getFillout_score() {
        return fillout_score;
    }

    public void setFillout_score(Integer fillout_score) {
        this.fillout_score = fillout_score;
    }

    public Integer getQues_ans_count() {
        return ques_ans_count;
    }

    public void setQues_ans_count(Integer ques_ans_count) {
        this.ques_ans_count = ques_ans_count;
    }

    public Integer getQues_ans_score() {
        return ques_ans_score;
    }

    public void setQues_ans_score(Integer ques_ans_score) {
        this.ques_ans_score = ques_ans_score;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getOper_user() {
        return oper_user;
    }

    public void setOper_user(String oper_user) {
        this.oper_user = oper_user;
    }

    public Date getOper_time() {
        return oper_time;
    }

    public void setOper_time(Date oper_time) {
        this.oper_time = oper_time;
    }
}
