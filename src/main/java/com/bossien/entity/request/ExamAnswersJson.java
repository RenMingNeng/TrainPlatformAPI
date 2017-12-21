package com.bossien.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by A on 2017/10/18.
 */
@ApiModel(description = "考试答题保存")
public class ExamAnswersJson implements Serializable {
    @ApiModelProperty("项目编号")
    String project_id;

    @ApiModelProperty("考试编号")
    String exam_no;

    @ApiModelProperty("用户编号")
    String user_id;

    @ApiModelProperty("考试时间")
    String exam_time;

    @ApiModelProperty("考试类型")
    String exam_type;

    @ApiModelProperty("考试分数")
    String exam_score;

    @ApiModelProperty("考试时长")
    String exam_duration;

  @ApiModelProperty("答题记录")
    List<ExamAnswersQuestionJson> answers;

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

    public String getExam_time() {
        return exam_time;
    }

    public void setExam_time(String exam_time) {
        this.exam_time = exam_time;
    }

    public String getExam_type() {
        return exam_type;
    }

    public void setExam_type(String exam_type) {
        this.exam_type = exam_type;
    }

    public String getExam_score() {
        return exam_score;
    }

    public void setExam_score(String exam_score) {
        this.exam_score = exam_score;
    }

    public String getExam_duration() {
        return exam_duration;
    }

    public void setExam_duration(String exam_duration) {
        this.exam_duration = exam_duration;
    }

    public List<ExamAnswersQuestionJson> getAnswers() {
        return answers;
    }

    public void setAnswers(List<ExamAnswersQuestionJson> answers) {
        this.answers = answers;
    }
}
