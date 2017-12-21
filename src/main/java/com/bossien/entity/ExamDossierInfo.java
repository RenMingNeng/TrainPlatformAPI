package com.bossien.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.text.DecimalFormat;

/**
 * 考试档案信息表
 */
@TableName("exam_dossier_info")
@ApiModel(description = "反馈实体对象")

public class ExamDossierInfo {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 项目id
     */
    @ApiModelProperty("项目id")
    private String project_id;

    /**
     * 已考试人数
     */
    @ApiModelProperty("已考试人数")
    private Integer yet_exam_count;

    /**
     * 未考试人数
     */
    @ApiModelProperty("未考试人数")
    private Integer not_exam_count;

    /**
     * 考试合格人数
     */
    @ApiModelProperty("考试合格人数")
    private Integer qualified_count;

    /**
     * 不合格人数
     */
    @ApiModelProperty("不合格人数")
    private Integer unqualified_count;

    /**
     * 考试合格率
     */
    @ApiModelProperty("考试合格率")
    private Double exam_pass_rate;

    public ExamDossierInfo(){}

    /**
     * 初始化构造函数
     * @param project_id
     * @param not_exam_count 未考人数 （默认总人数)
     */
    public ExamDossierInfo(String project_id, Integer not_exam_count){
        this.project_id = project_id;
        this.not_exam_count = not_exam_count;
        this.yet_exam_count = 0;
        this.qualified_count = 0;
        this.unqualified_count = 0;
        this.exam_pass_rate = 0.0;
    }

    /**
     * 修改构造方法
     * @param project_id
     * @param yet_exam_count 已考人数
     * @param not_exam_count 未考人数
     * @param qualified_count 合格人数
     * @param unqualified_count 不合格人数
     */
    public ExamDossierInfo(String project_id, Integer yet_exam_count, Integer not_exam_count,
                           Integer qualified_count, Integer unqualified_count){
        this.project_id = project_id;
        this.yet_exam_count = yet_exam_count;
        this.not_exam_count = not_exam_count;
        this.qualified_count = qualified_count;
        this.unqualified_count = unqualified_count;

        this.exam_pass_rate = Double.parseDouble(new DecimalFormat("0.0").
                format(qualified_count * 100 / yet_exam_count));
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public Integer getYet_exam_count() {
        return yet_exam_count;
    }

    public void setYet_exam_count(Integer yet_exam_count) {
        this.yet_exam_count = yet_exam_count;
    }

    public Integer getNot_exam_count() {
        return not_exam_count;
    }

    public void setNot_exam_count(Integer not_exam_count) {
        this.not_exam_count = not_exam_count;
    }

    public Integer getQualified_count() {
        return qualified_count;
    }

    public void setQualified_count(Integer qualified_count) {
        this.qualified_count = qualified_count;
    }

    public Integer getUnqualified_count() {
        return unqualified_count;
    }

    public void setUnqualified_count(Integer unqualified_count) {
        this.unqualified_count = unqualified_count;
    }

    public Double getExam_pass_rate() {
        return exam_pass_rate;
    }

    public void setExam_pass_rate(Double exam_pass_rate) {
        this.exam_pass_rate = exam_pass_rate;
    }
}
