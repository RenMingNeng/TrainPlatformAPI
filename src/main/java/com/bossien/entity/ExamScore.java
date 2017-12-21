package com.bossien.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 考试分数记录表
 */
@TableName("exam_score")
@ApiModel(description = "反馈实体对象")
public class ExamScore {
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
     * 考试编号
     */
    @ApiModelProperty("考试编号")
    private String exam_no;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private String user_id;

    /**
     * 考试类型1：模拟考试2：正式考试
     */
    @ApiModelProperty("考试类型1：模拟考试2：正式考试")
    private String exam_type;

    /**
     * 考试成绩
     */
    @ApiModelProperty("考试成绩")
    private Integer score;

    /**
     * 考试开始时间
     */
    @ApiModelProperty("考试开始时间")
    private String exam_time;

    /**
     * 是否合格:1: 是2: 否
     */
    @ApiModelProperty("是否合格:1: 是2: 否")
    private String is_passed;

    /**
     * 考试时长
     */
    @ApiModelProperty("考试时长")
    private Double exam_duration;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String create_user;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private String create_time;

    /**
     * 操作人
     */
    @ApiModelProperty("操作人")
    private String oper_user;

    /**
     * 操作时间
     */
    @ApiModelProperty("操作时间")
    private String oper_time;

     public ExamScore(){}
    public ExamScore(String project_id,String user_id){
        this.project_id = project_id;
        this.user_id = user_id;
    }
     public ExamScore(String project_id,String user_id,String exam_type){
         this.project_id = project_id;
         this.user_id = user_id;
         this.exam_type = exam_type;
    }

    public ExamScore(String project_id, String exam_no,String user_id,String exam_type, Integer score,
                     String exam_time, String is_passed, Double exam_duration){
        this.project_id = project_id;
        this.exam_no = exam_no;
        this.user_id = user_id;
        this.exam_type = exam_type;

        this.score = score;
        this.exam_time = exam_time;
        this.is_passed = is_passed;
        this.exam_duration = exam_duration;
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

    public String getExam_no() {
        return exam_no;
    }

    public void setExam_no(String exam_no) {
        this.exam_no = exam_no;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getExam_type() {
        return exam_type;
    }

    public void setExam_type(String exam_type) {
        this.exam_type = exam_type;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getExam_time() {
        return exam_time;
    }

    public void setExam_time(String exam_time) {
        this.exam_time = exam_time;
    }

    public String getIs_passed() {
        return is_passed;
    }

    public void setIs_passed(String is_passed) {
        this.is_passed = is_passed;
    }

    public Double getExam_duration() {
        return exam_duration;
    }

    public void setExam_duration(Double exam_duration) {
        this.exam_duration = exam_duration;
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
