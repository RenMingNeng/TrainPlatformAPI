package com.bossien.entity.enumeration;
/**
 * 课程类型枚举类
 * @author chensl
 *
 */
public enum CourseTypeEnum {
	WEIKE_VIDEO("公共安全", "1"),
	SHIGU_ANLI("事故管理", "2"),
	ZHISHI_JX("知识库", "3"),
	GUANLI_WENJIAN("管理文件", "4"),
	MORE("更多", "5"),
	SEARCH("搜索", "6");
    // 枚举中文
    private String name;
    // 枚举值
    private String value;
    
	// 枚举翻译
	public static CourseTypeEnum getEnum(String value) {
        CourseTypeEnum[] is = CourseTypeEnum.values();
		for (CourseTypeEnum i : is) {
			if (!value.equals(i.getValue())) {
				continue;
			}
			return i;
		}
		return null;
	}
    // 构造
    CourseTypeEnum(String name, String value) {
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
