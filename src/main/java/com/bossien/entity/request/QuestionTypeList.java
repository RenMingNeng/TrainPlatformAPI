package com.bossien.entity.request;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/20.
 */
public class QuestionTypeList implements Serializable{

    private String user_id;
    private String project_id;
    private String questions_type;
    private Integer questions_count;
    private Integer page_index=1;
    private Integer page_size=10;

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

    public String getQuestions_type() {
        return questions_type;
    }

    public void setQuestions_type(String questions_type) {
        this.questions_type = questions_type;
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

    public Integer getQuestions_count() {
        return questions_count;
    }

    public void setQuestions_count(Integer questions_count) {
        this.questions_count = questions_count;
    }
}
