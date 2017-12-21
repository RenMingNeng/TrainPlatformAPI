package com.bossien.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 部门信息
 */

@ApiModel(description = "部门实体对象")
@TableName("department")
public class Department implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("id")
    private String id;

    /** 所属单位编号 */
    @ApiModelProperty("company_id")
    private String company_id;
    /** 部门名称 */
    @ApiModelProperty("dept_name")
    private String dept_name;

    /** 部门负责人 */
    @ApiModelProperty("director")
    private String director;

    /** 部门负责人 */
    @ApiModelProperty("parent_id")
    private String parent_id;

    /** 是否有效
     1: 是
     2: 否 */
    @ApiModelProperty("is_valid")
    private String is_valid;

    /** 排序号 */
    @ApiModelProperty("order_num")
    private String order_num;

    private String create_user;
    private String create_time;
    private String oper_user;
    private String oper_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(String is_valid) {
        this.is_valid = is_valid;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getOper_user() {
        return oper_user;
    }

    public void setOper_user(String oper_user) {
        this.oper_user = oper_user;
    }

    public String getOper_time() {
        return oper_time;
    }

    public void setOper_time(String oper_time) {
        this.oper_time = oper_time;
    }
}
