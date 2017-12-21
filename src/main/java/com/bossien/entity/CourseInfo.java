package com.bossien.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程信息
 */

@ApiModel(description = "反馈实体对象")
@TableName("course_info")
public class CourseInfo implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("id")
    private String id;

    /** 课程id */
    @ApiModelProperty("课程id")
    private String course_id;

    /** 课程编号 */
    @ApiModelProperty("密码")
    private String course_no;

    /** 课程名称 */
    @ApiModelProperty("课程名称")
    private String course_name;

    /** 课程类型id */
    @ApiModelProperty("课程类型id")
    private Integer couse_type_id;

    /** 课程类型名称 */
    @ApiModelProperty("课程类型名称")
    private String couse_type_name;

    /** 课时 */
    @ApiModelProperty("课时")
    private Integer class_hour;

    /** 题量 */
    @ApiModelProperty("题量")
    private Integer question_count;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String create_user;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date create_time;

    /**
     * 操作人
     */
    @ApiModelProperty("操作人")
    private String oper_user;

    /**
     * 操作时间
     */
    @ApiModelProperty("操作时间")
    private String oper_time;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getOper_user() {
        return oper_user;
    }

    public void setOper_user(String oper_user) {
        this.oper_user = oper_user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_no() {
        return course_no;
    }

    public void setCourse_no(String course_no) {
        this.course_no = course_no;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Integer getCouse_type_id() {
        return couse_type_id;
    }

    public void setCouse_type_id(Integer couse_type_id) {
        this.couse_type_id = couse_type_id;
    }

    public String getCouse_type_name() {
        return couse_type_name;
    }

    public void setCouse_type_name(String couse_type_name) {
        this.couse_type_name = couse_type_name;
    }

    public Integer getClass_hour() {
        return class_hour;
    }

    public void setClass_hour(Integer class_hour) {
        this.class_hour = class_hour;
    }

    public Integer getQuestion_count() {
        return question_count;
    }

    public void setQuestion_count(Integer question_count) {
        this.question_count = question_count;
    }
}
