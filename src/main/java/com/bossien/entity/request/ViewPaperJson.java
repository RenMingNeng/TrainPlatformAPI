package com.bossien.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by A on 2017/10/18.
 */
@ApiModel(description = "试卷预览")
public class ViewPaperJson implements Serializable {
    @ApiModelProperty("试卷编号")
    String exam_no;

    @ApiModelProperty("用户编号")
    String user_id;

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
}
