package com.bossien.entity.enumeration;

/**
 *
 * 项目培训完成状态枚举
 */
public enum TrainStatueEnum {
    TrainStatue_1("1", "未完成"),
    TrainStatue_2("2", "已完成");

    private String value;
    private String name;

    TrainStatueEnum(String value, String name) {
        this.name = name;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static TrainStatueEnum get(String value) {
        TrainStatueEnum[] values = TrainStatueEnum.values();
        for (TrainStatueEnum object : values) {
            if (object.value.equals(value)) {
                return object;
            }
        }
        return null;
    }

}