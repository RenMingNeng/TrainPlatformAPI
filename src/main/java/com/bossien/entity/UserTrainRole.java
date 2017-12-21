package com.bossien.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/15.
 */
public class UserTrainRole implements Serializable {

    private String user_id;
    private String train_role_id;
    private String role_name;
    private String is_valid;
    private String create_user;
    private String create_time;
    private String oper_user;
    private String oper_time;

    public UserTrainRole() {
    }

    public UserTrainRole(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTrain_role_id() {
        return train_role_id;
    }

    public void setTrain_role_id(String train_role_id) {
        this.train_role_id = train_role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(String is_valid) {
        this.is_valid = is_valid;
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

    public enum IsValid {
        TYPE_1("有效", "1"), TYPE_2("无效", "2");
        // 枚举中文
        private String name;
        // 枚举值
        private String value;

        // 枚举翻译
        public static UserTrainRole.IsValid getEnum(String value) {
            UserTrainRole.IsValid[] is = UserTrainRole.IsValid.values();
            for (UserTrainRole.IsValid i : is) {
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
