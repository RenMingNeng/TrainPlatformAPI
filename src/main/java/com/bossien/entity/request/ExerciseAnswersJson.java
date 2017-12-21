package com.bossien.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by A on 2017/10/18.
 */
@ApiModel(description = "练习答题保存")
public class ExerciseAnswersJson implements Serializable {
    @ApiModelProperty("项目编号")
    String project_id;

    @ApiModelProperty("用户编号")
    String user_id;

    @ApiModelProperty("答题记录")
    List<ExerciseAnswersInfoJson> answers;

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_Id) {
        this.project_id = project_Id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public List<ExerciseAnswersInfoJson> getAnswers() {
        return answers;
    }

    public void setAnswers(List<ExerciseAnswersInfoJson> answers) {
        this.answers = answers;
    }
}
