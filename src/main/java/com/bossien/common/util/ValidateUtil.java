package com.bossien.common.util;

import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.HashMap;
import java.util.Map;

public class ValidateUtil {

	public static Map<String, Object> toStringJson(BindingResult result){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		for(FieldError fieldError : result.getFieldErrors()){
			map.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return map;
		
	}

    public static boolean minLength(String str, int len) {
		if(StringUtils.isEmpty(str))
			return false;
		if(str.length() < len)
			return false;
		return true;
    }

	public static boolean maxLength(Integer it, int len) {
		if(null == it)
			return true;
		if(it.intValue() > len)
			return false;
		return true;
	}
}
