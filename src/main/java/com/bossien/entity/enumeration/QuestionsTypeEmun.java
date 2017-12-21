package com.bossien.entity.enumeration;

import java.util.List;

/**
 * Created by huangzhaoyong on 2017/7/31.
 *  题库类型
 */
public enum QuestionsTypeEmun {
    //题目类型：01.单选题 02.多选题 03.判断题 04.填空题 05.简答题 06.论述题 07.分析题
    QUESTIONTYPE_SINGLE("01", "单选"),
    QUESTIONTYPE_MANY("02", "多选"),
    QUESTIONTYPE_JUDGE("03", "判断"),
    QUESTIONTYPE_FILLOUT("04", "填空"),
    QUESTIONTYPE_QUESANS("05", "简答"),
    QUESTIONTYPE_LUNSHU("06", "论述"),
    QUESTIONTYPE_FENXI("07", "分析"),
    QUESTIONTYPE_YICUO("08", "易错");

    private String value;
    private String name;

    QuestionsTypeEmun(String value, String name) {
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
        QuestionsTypeEmun[] arys = QuestionsTypeEmun.values();
        for (QuestionsTypeEmun type: arys) {
            if(type.value.equals(value)){
                return type.name;
            }
        }
        return "";
    }

    public static List<Object> getValues() {

        return QuestionsTypeEmun.getValues();
    }
}
