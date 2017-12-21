package com.bossien.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 *
 * 反馈
 *
 */
@TableName("feedback")
@ApiModel(description = "反馈实体对象")
public class Feedback implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@TableId(type = IdType.UUID)
	@ApiModelProperty("id")
	private String id;

	/**
	 * 账户id
	 */
	@NotEmpty(message = "账户id不能为空")
	@ApiModelProperty("账户id")
	private String user_id;

	/**
	 * 账户名称
	 */
	@NotEmpty(message = "账户名称不能为空")
	@ApiModelProperty("账户名称")
	private String user_name;

	/**
	 * 问题类型
	 */
	@NotEmpty(message = "问题类型不能为空")
	@ApiModelProperty("问题类型")
	private String problem_type;

	/**
	 * 问题描述
	 */
	@ApiModelProperty("问题描述")
	private String content;

	/**
	 * 联系方式
	 */
	@ApiModelProperty("联系方式")
	private String contact_way;

	/**
	 * 上传附件
	 */
	@ApiModelProperty("上传附件")
	private String attchments;

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
	 * 反馈类型
	 */
	@ApiModelProperty("反馈类型")
	private String problem_status;

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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getProblem_type() {
		return problem_type;
	}

	public void setProblem_type(String problem_type) {
		this.problem_type = problem_type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContact_way() {
		return contact_way;
	}

	public void setContact_way(String contact_way) {
		this.contact_way = contact_way;
	}

	public String getAttchments() {
		return attchments;
	}

	public void setAttchments(String attchments) {
		this.attchments = attchments;
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

	public String getProblem_status() {
		return problem_status;
	}

	public void setProblem_status(String problem_status) {
		this.problem_status = problem_status;
	}
}
