package com.bossien.entity.enumeration;

import java.util.List;

/**
 * Created by huangzhaoyong on 2017/7/31.
 *  题库类型
 */
public enum ClassHourSourceEmun {
    // 学时来源-1：学习、1：练习答题（正确）、2：练习答题（错误）、3：考试答题（正确）、4：考试答题（错误）',
    CLASS_HOUR_SOURCE_1("-1", "学习"),
    CLASS_HOUR_SOURCE_2("1", "练习答题（正确）"),
    CLASS_HOUR_SOURCE_3("2", "练习答题（错误）"),
    CLASS_HOUR_SOURCE_4("3", "考试答题（正确）"),
    CLASS_HOUR_SOURCE_5("4", "考试答题（错误）");


    private String value;
    private String name;

    ClassHourSourceEmun(String value, String name) {
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

    public static String getName(String value) {
        ClassHourSourceEmun[] arys = ClassHourSourceEmun.values();
        for (ClassHourSourceEmun type: arys) {
            if(type.value.equals(value)){
                return type.name;
            }
        }
        return "";
    }

    public static List<Object> getValues() {

        return ClassHourSourceEmun.getValues();
    }
}
