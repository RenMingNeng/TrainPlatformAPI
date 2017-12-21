package com.bossien.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * user
 */
@TableName("user")
public class User implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@ApiModelProperty("id")
	private String id;

	/** 用户名 */
	@ApiModelProperty("用户名")
	private String user_account;

	/** 密码 */
	@ApiModelProperty("密码")
	private String user_passwd;

	/** 姓名 */
	@ApiModelProperty("姓名")
	private String user_name;

	/** 手机号 */
	@ApiModelProperty("手机号")
	private String mobile_no;

	/** 单位id */
	@ApiModelProperty("单位id")
	private String company_id;

	/** 单位名称 */
	@ApiModelProperty("单位名称")
	private String company_name;

	/** 部门id */
	@ApiModelProperty("部门id")
	private String department_id;

	/** 部门名称 */
	@ApiModelProperty("部门名称")
	private String department_name;

	/** 有效性 */
	@ApiModelProperty("有效性")
	private String is_valid;


	public User() { }

	public User(String user_account) {
		this.user_account = user_account;
	}

	public User(String user_account, String user_passwd) {
		this.user_account = user_account;
		this.user_passwd = user_passwd;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	public String getUser_passwd() {
		return user_passwd;
	}

	public void setUser_passwd(String user_passwd) {
		this.user_passwd = user_passwd;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getIs_valid() {
		return is_valid;
	}

	public void setIs_valid(String is_valid) {
		this.is_valid = is_valid;
	}

	// 用户是否有效 1：有效、2：无效
	public enum IsValid {
		TYPE_1("有效", "1"), TYPE_2("无效", "2");
		// 枚举中文
		private String name;
		// 枚举值
		private String value;

		// 枚举翻译
		public static IsValid getEnum(String value) {
			IsValid[] is = IsValid.values();
			for (IsValid i : is) {
				if (!value.equals(i.getValue())) {
					continue;
				}
				return i;
			}
			return null;
		}

		// 构造
		IsValid(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
