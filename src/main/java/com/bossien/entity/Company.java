package com.bossien.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 *
 * 单位
 *
 */
@ApiModel(description = "反馈实体对象")
@TableName("ap_company")
public class Company implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@ApiModelProperty("intId")
	private Integer intId;

	/**
	 * 单位编号
	 */
	@NotEmpty(message = "单位编号不能为空")
	@ApiModelProperty("单位编号")
	private String varCode;

	/**
	 * 单位名称
	 */
	@NotEmpty(message = "单位名称不能为空")
	@ApiModelProperty("单位名称")
	private String varName;

	/**
	 * 有效标志
	 */
	@ApiModelProperty("有效标志(1：有效 2：无效)")
	private String chrIsValid;

	public Integer getIntId() {
		return intId;
	}
	public void setIntId(Integer intId) {
		this.intId = intId;
	}
	public String getVarCode() {
		return varCode;
	}

	public void setVarCode(String varCode) {
		this.varCode = varCode;
	}

	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	public String getChrIsValid() {
		return chrIsValid;
	}

	public void setChrIsValid(String chrIsValid) {
		this.chrIsValid = chrIsValid;
	}

	// 单位是否有效 1：有效、2：无效
	public enum ChrIsValid {
		TYPE_1("有效", "1"), TYPE_2("无效", "2");
		// 枚举中文
		private String name;
		// 枚举值
		private String value;

		// 枚举翻译
		public static Company.ChrIsValid getEnum(String value) {
			Company.ChrIsValid[] is = Company.ChrIsValid.values();
			for (Company.ChrIsValid i : is) {
				if (!value.equals(i.getValue())) {
					continue;
				}
				return i;
			}
			return null;
		}

		// 构造
		ChrIsValid(String name, String value) {
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
