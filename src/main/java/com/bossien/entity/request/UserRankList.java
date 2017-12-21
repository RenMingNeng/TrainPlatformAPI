package com.bossien.entity.request;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/20.
 */
public class UserRankList implements Serializable{

    private String project_id;
    private Integer page_index=1;
    private Integer page_size=10;

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
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
