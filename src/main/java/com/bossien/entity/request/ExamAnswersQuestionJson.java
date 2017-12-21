package com.bossien.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by A on 2017/10/19.
 */
@ApiModel(description = "考试答题中试题json")
public class ExamAnswersQuestionJson implements Serializable{
    @ApiModelProperty("项目编号")
    String question_id;

    @ApiModelProperty("考试编号")
    String course_id;

    @ApiModelProperty("用户编号")
    String answer;

    @ApiModelProperty("考试时间")
    String is_correct;

    @ApiModelProperty("考试类型")
    Integer question_score;

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
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

    public Integer getQuestion_score() {
        return question_score;
    }

    public void setQuestion_score(Integer question_score) {
        this.question_score = question_score;
    }
}
