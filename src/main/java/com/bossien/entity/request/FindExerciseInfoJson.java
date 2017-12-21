package com.bossien.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by A on 2017/10/18.
 */
@ApiModel(description = "练习答题保存")
public class FindExerciseInfoJson implements Serializable {
    @ApiModelProperty("项目编号")
    String project_id;

    @ApiModelProperty("用户编号")
    String user_id;

    //选择查询类型 1 查看答对试题 2 查看答错试题
    @ApiModelProperty("选择查询类型")
    String is_correct;

    @ApiModelProperty("答对或者答错的数量")
    Integer answer_count;

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

    public String getIs_correct() {
        return is_correct;
    }

    public void setIs_correct(String is_correct) {
        this.is_correct = is_correct;
    }

    public Integer getAnswer_count() {
        return answer_count;
    }

    public void setAnswer_count(Integer answer_count) {
        this.answer_count = answer_count;
    }
}
