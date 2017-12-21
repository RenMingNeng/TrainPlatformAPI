package com.bossien.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/31.
 */
public class Role implements Serializable {

    private String id;
    private String role_name;
    private String role_desc;

    public Role(String id, String role_name, String role_desc) {
        this.id = id;
        this.role_name = role_name;
        this.role_desc = role_desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_desc() {
        return role_desc;
    }

    public void setRole_desc(String role_desc) {
        this.role_desc = role_desc;
    }
}
