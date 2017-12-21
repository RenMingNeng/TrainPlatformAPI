package com.bossien.entity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;


/**
 * 考试答案表
 */
@TableName("exam_answers")
@ApiModel(description = "反馈实体对象")

public class ExamAnswers {

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
     * 试题id
     */
    @ApiModelProperty("试题编号")
    private String question_id;

    /**
     * 用户答案
     */
    @ApiModelProperty("用户答案")
    private String answer;


    /**
     * 是否正确 1：是 2：否
     */
    @ApiModelProperty("是否正确 1：是 2：否")
    private String is_correct;

    /**
     * 答题得分
     */
    @ApiModelProperty("答题得分")
    private Integer score;

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

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getIs_correct() {
        return is_correct;
    }

    public void setIs_correct(String is_correct) {
        this.is_correct = is_correct;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getOper_user() {
        return oper_user;
    }

    public void setOper_user(String oper_user) {
        this.oper_user = oper_user;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getOper_time() {
        return oper_time;
    }

    public void setOper_time(String oper_time) {
        this.oper_time = oper_time;
    }
}
