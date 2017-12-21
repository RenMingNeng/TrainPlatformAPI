package com.bossien.entity.request;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/20.
 */
public class ProjectTypeList implements Serializable{

    private String user_id;
    private String status;
    private Integer page_index=1;
    private Integer page_size=10;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
