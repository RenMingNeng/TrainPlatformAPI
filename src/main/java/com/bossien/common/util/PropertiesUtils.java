package com.bossien.common.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class PropertiesUtils {

    private static Properties p = new Properties();

    private static Map<String, String> propertieMap = new HashMap<String, String>();


    /**
     * 读取applicationContext.properties配置文件信息
     */
    static {
        try {
            p.load(PropertiesUtils.class.getClassLoader().getResourceAsStream("properties/api.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据key得到value的值
     */
    public static String getValue(String key) {
        String result = propertieMap.get(key);
        if (result == null) {
            result = p.getProperty(key);
            propertieMap.put(key, result);
        }
        return result;
    }


    /**
     * 根据key值修改value
     */
    public static void setValue(Map<String, String> map) {
        try {
            if (map != null) {
                OutputStream out = null;

                out = new FileOutputStream(String.valueOf(PropertiesUtils.class.getClassLoader().getResourceAsStream("properties/api.properties")));
                //获取key值集合
                Set<String> keys = map.keySet();
                for (String key : keys) {

                    p.setProperty(key, map.get(key));
                    propertieMap.put(key, map.get(key));
                }
                p.store(out, "update properties");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

	/**
	 * 读取properties所有键和值
	 * @return
	 */
	public static List<List<String>> getValues(){
		List<List<String>> properties = new ArrayList<List<String>>();
		Set<String> keys = p.stringPropertyNames();
		for (String key:keys) {
			List<String> strs = new ArrayList<String>();
			String value = p.getProperty(key);
			strs.add(key);
			strs.add(value);
			properties.add(strs);
		}
		return properties;
	}

    /**
     * 读取所有的配置文件信息
     * */
    public static Map<String, String> getAllProperties(){
        Map<String, String> map = new HashMap<String, String>();
        Enumeration en = p.propertyNames();
        String key  = "";
        String value = "";
        while(en.hasMoreElements()){
            key = (String)en.nextElement();
            if(key.contains("version")){
                value = p.getProperty(key);
                map.put(key, value);
            }
        }
        return map;
    }
}
