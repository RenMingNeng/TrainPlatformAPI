package com.bossien.entity.request;

import java.io.Serializable;

/**
 * 最近学习和最新上传的课程接口入参
 * Created by Administrator on 2017/10/18.
 */
public class LatelyStudyCourse implements Serializable {

     private String company_id;
     private String course_type;
     private String user_id;

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getCourse_type() {
        return course_type;
    }

    public void setCourse_type(String course_type) {
        this.course_type = course_type;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
