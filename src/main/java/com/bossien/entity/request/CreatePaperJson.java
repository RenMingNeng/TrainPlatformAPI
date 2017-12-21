package com.bossien.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by A on 2017/10/18.
 */
@ApiModel(description = "创建考卷参数")
public class CreatePaperJson implements Serializable {
    @ApiModelProperty("项目编号")
    String project_id;

    @ApiModelProperty("用户编号")
    String user_id;

    @ApiModelProperty("考试类型(1：模拟考试2：正式考试)")
    String exam_type;

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

    public String getExam_type() {
        return exam_type;
    }

    public void setExam_type(String exam_type) {
        this.exam_type = exam_type;
    }
}
