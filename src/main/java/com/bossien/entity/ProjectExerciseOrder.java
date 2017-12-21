package com.bossien.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 *
 * 练习统计
 *
 */
@ApiModel(description = "用户练习统计对象")
@TableName("ap_company")
public class ProjectExerciseOrder implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@ApiModelProperty("id")
	private String id;

	/**
	 * 单位编号
	 */
	@NotEmpty(message = "项目编号不能为空")
	@ApiModelProperty("项目编号")
	private String project_id;

	/**
	 * 用户编号
	 */
	@NotEmpty(message = "用户编号不能为空")
	@ApiModelProperty("用户编号")
	private String user_id;

	/**
	 * 用户编号
	 */
	@ApiModelProperty("角色编号")
	private String role_id;

	/**
	 * 总体量
	 */
	@ApiModelProperty("总体量")
	private Integer total_question;

	/**
	 * 已答题量
	 */
	@ApiModelProperty("已答题量")
	private Integer yet_answered;

	/**
	 * 总体量
	 */
	@ApiModelProperty("未答题量")
	private Integer not_answered;

	/**
	 * 答对题量
	 */
	@ApiModelProperty("答对题量")
	private Integer correct_answered;

	/**
	 * 答错题量
	 */
	@ApiModelProperty("答错题量")
	private Integer fail_answered;

	/**
	 * 答题正确率：答对总题量/已答题量*100
	 */
	@ApiModelProperty("答题正确率")
	private Double correct_rate;

	/**
	 * 答题学时(单位秒）
	 */
	@ApiModelProperty("答题学时")
	private Long answer_studytime;

	private String oper_user;

	private String oper_time;

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public ProjectExerciseOrder(){}
	public ProjectExerciseOrder(String project_id, String user_id){
		this.project_id = project_id;
		this.user_id = user_id;
	}

	public ProjectExerciseOrder(String project_id, String user_id, Integer total_question, Integer yet_answered,
								Integer not_answered, Integer correct_answered, Integer fail_answered,
								Double correct_rate, Long answer_studytime, String oper_user, String oper_time){
		this.project_id = project_id;
		this.user_id = user_id;
		this.total_question = total_question;
		this.yet_answered = yet_answered;
		this.not_answered = not_answered;
		this.correct_answered = correct_answered;
		this.fail_answered = fail_answered;
		this.correct_rate = correct_rate;
		this.answer_studytime = answer_studytime;
		this.oper_user = oper_user;
		this.oper_time = oper_time;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Integer getTotal_question() {
		return total_question;
	}

	public void setTotal_question(Integer total_question) {
		this.total_question = total_question;
	}

	public Integer getYet_answered() {
		return yet_answered;
	}

	public void setYet_answered(Integer yet_answered) {
		this.yet_answered = yet_answered;
	}

	public Integer getNot_answered() {
		return not_answered;
	}

	public void setNot_answered(Integer not_answered) {
		this.not_answered = not_answered;
	}

	public Integer getCorrect_answered() {
		return correct_answered;
	}

	public void setCorrect_answered(Integer correct_answered) {
		this.correct_answered = correct_answered;
	}

	public Integer getFail_answered() {
		return fail_answered;
	}

	public void setFail_answered(Integer fail_answered) {
		this.fail_answered = fail_answered;
	}

	public Double getCorrect_rate() {
		return correct_rate;
	}

	public void setCorrect_rate(Double correct_rate) {
		this.correct_rate = correct_rate;
	}

	public Long getAnswer_studytime() {
		return answer_studytime;
	}

	public void setAnswer_studytime(Long answer_studytime) {
		this.answer_studytime = answer_studytime;
	}
}
