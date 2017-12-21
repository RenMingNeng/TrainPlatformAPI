package com.bossien.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/7.
 */
@ApiModel(description="收藏(取消)错题请求json对象")
public class QuestionCollectJson implements Serializable {

    @ApiModelProperty("账户id")
    private String user_id;

    @ApiModelProperty("项目id")
    private String project_id;

    @ApiModelProperty("题目id")
    private String question_id;

    @ApiModelProperty("收藏/取消收藏")
    private String action;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
