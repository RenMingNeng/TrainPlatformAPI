package com.bossien.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 考试试题表
 */
@TableName("exam_question")
@ApiModel(description = "反馈实体对象")
public class ExamQuestion {
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
     * 考题id
     */
    @ApiModelProperty("考题id")
    private String questions_id;


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

    public ExamQuestion(){}

    public ExamQuestion(String exam_no){
        this.exam_no = exam_no;
    }

    public ExamQuestion(String exam_no, String user_id){
        this.exam_no = exam_no;
        this.user_id = user_id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getQuestions_id() {
        return questions_id;
    }

    public void setQuestions_id(String questions_id) {
        this.questions_id = questions_id;
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
