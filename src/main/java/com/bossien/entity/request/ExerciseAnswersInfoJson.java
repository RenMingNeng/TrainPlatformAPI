package com.bossien.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by A on 2017/10/19.
 */
@ApiModel(description = "练习答题保存详情")
public class ExerciseAnswersInfoJson {
    @ApiModelProperty("试题编号")
    String question_id;

    @ApiModelProperty("课程编号")
    String course_id;

    @ApiModelProperty("答案")
    String answer;

    @ApiModelProperty("是否答对")
    String is_correct;

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
}
