package com.bossien.entity.request;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 保存视频播放位置接口入参
 * Created by Administrator on 2017/10/18.
 */
public class SaveVideoPosition implements Serializable {

     private String project_id;
     private String user_id;
     private List<Map<String, Object>> watch_positions;//视频位置列表
     private String file_id;//视频Id
     private Integer last_position;//上次播放位置
     private Integer study_time;//本次学习时长
     private String course_id;

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

    public List<Map<String, Object>> getWatch_positions() {
        return watch_positions;
    }

    public void setWatch_positions(List<Map<String, Object>> watch_positions) {
        this.watch_positions = watch_positions;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public Integer getLast_position() {
        return last_position;
    }

    public void setLast_position(Integer last_position) {
        this.last_position = last_position;
    }

    public Integer getStudy_time() {
        return study_time;
    }

    public void setStudy_time(Integer study_time) {
        this.study_time = study_time;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }
}
