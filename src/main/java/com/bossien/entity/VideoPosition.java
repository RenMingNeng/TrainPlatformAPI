package com.bossien.entity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 试卷组卷策略
 */
@TableName("video_position")
@ApiModel(description = "记录视频位置实体对象")

public class VideoPosition {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("id")
    private String id;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private String user_id;

    /**
     * 课程id
     */
    @ApiModelProperty("课程id")
    private String course_id;

    /**
     * 视频id
     */
    @ApiModelProperty("视频id")
    private String video_id;

    /**
     * 上次学习位置
     */
    @ApiModelProperty("上次学习位置")
    private Integer last_position;


    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private String create_time;

    /**
     * 操作时间
     */
    @ApiModelProperty("操作时间")
    private String oper_time;
    public VideoPosition(){}
    public VideoPosition(String user_id,String course_id,String video_id){
        this.user_id = user_id;
        this.course_id = course_id;
        this.video_id = video_id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public Integer getLast_position() {
        return last_position;
    }

    public void setLast_position(Integer last_position) {
        this.last_position = last_position;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getOper_time() {
        return oper_time;
    }

    public void setOper_time(String oper_time) {
        this.oper_time = oper_time;
    }
}
