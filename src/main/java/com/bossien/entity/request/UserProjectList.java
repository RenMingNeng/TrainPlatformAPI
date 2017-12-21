package com.bossien.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/7.
 */
@ApiModel(description="用户参加的项目集合请求json对象")
public class UserProjectList implements Serializable {

    @ApiModelProperty("账户id")
    private String user_id;

    @ApiModelProperty("项目状态（1：未发布，2：未开始 3：进行中，4：已结束）")
    private String project_status;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProject_status() {
        return project_status;
    }

    public void setProject_status(String project_status) {
        this.project_status = project_status;
    }
}
