package com.bossien.entity;

import java.io.Serializable;

/**
 * Created by A on 2017/8/1.
 */
public class CourseQuestion implements Serializable {
    private static final long serialVersionUID = 7824480199062571903L;
    private String intId;
    private String intCourseId;
    private String intQuestionId;

    public String getIntId() {
        return intId;
    }

    public void setIntId(String intId) {
        this.intId = intId;
    }

    public String getIntCourseId() {
        return intCourseId;
    }

    public void setIntCourseId(String intCourseId) {
        this.intCourseId = intCourseId;
    }

    public String getIntQuestionId() {
        return intQuestionId;
    }

    public void setIntQuestionId(String intQuestionId) {
        this.intQuestionId = intQuestionId;
    }
}
