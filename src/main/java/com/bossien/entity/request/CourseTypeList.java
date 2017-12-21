package com.bossien.entity.request;

import java.io.Serializable;

/**
 * 课程分类下所有课程集合接口入参
 * Created by Administrator on 2017/10/18.
 */
public class CourseTypeList implements Serializable {

     private String company_id;
     //1.	微课视频2.	事故案例3.	知识讲义4.	管理文件5 更多6 全局搜索
     private String course_type;
     //用户idcourseType为5时必填
     private String user_id;
     //课程名称（首页搜索时模糊查询参数）
     private String course_name;
     private Integer page_index=1;
     private Integer page_size=10;

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

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Integer getPage_index() {
        return page_index;
    }

    public void setPage_index(Integer page_index) {
        this.page_index = page_index;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }
}
