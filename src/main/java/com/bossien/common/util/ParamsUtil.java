package com.bossien.common.util;

public class ParamsUtil {
    private static final String PERCENT = "%";

    public static String joinLike(String str){
        if(StringUtil.isEmpty(str)){
            return "";
        }else{
            return PERCENT.concat(str).concat(PERCENT);
        }
    }
}
