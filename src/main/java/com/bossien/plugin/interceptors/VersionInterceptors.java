package com.bossien.plugin.interceptors;

import com.bossien.common.util.PropertiesUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * app版本是否升级拦截器
 * Created by Administrator on 2017/11/10.
 */
public class VersionInterceptors extends HandlerInterceptorAdapter {

    private static final Logger logger = LogManager.getLogger(VersionInterceptors.class);
    private static final String DEFAULT_APP_TYPE = "appType"; //1: android版  2:ios版
    private static final String DEFAULT_VERSION_CODE = "versionCode";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String android_type=PropertiesUtils.getValue("android_type");
        String ios_type=PropertiesUtils.getValue("ios_type");
        //本地android版本号
        String android_code=PropertiesUtils.getValue("android_version_code");
        //本地ios版本号
        String ios_code=PropertiesUtils.getValue("ios_version_code");
        //升级地址
        String update_version_url=PropertiesUtils.getValue("update_version_url");

            //客户端app类型
            String appType=request.getHeader(DEFAULT_APP_TYPE);
           logger.info("客户端app类型===================="+appType);
            //客户端版本号
            String versionCode=request.getHeader(DEFAULT_VERSION_CODE);
           logger.info("客户端版本号===================="+versionCode);
       try {
          if(appType!=null && versionCode!=null){
                if(appType.equals(android_type)){ //如果是android版本
                    //检查是否需要升级 比较版本号
                    logger.info("本地版本号===================="+android_code);
                   // boolean flag = compare(android_code,versionCode);
                //    logger.info("是否升级===================="+flag);
                    if(!versionCode.equals(android_code)){
                        //response.setHeader("url",update_version_url);
                         String str="{\n" +
                                 "  \"meta\": {\n" +
                                 "    \"success\": false,\n" +
                                 "    \"message\": \"update\"\n" +
                                 "  },\n" +
                                 "  \"update_url\": \"update_version_url\"\n" +
                                 "}";
                        String str2=str.replace("update_version_url",update_version_url);
                        response.getWriter().write(str2);

                        return false;
                    }
                    //如果是ios版本
                }else if(appType.equals(ios_type)){
                    if(!versionCode.equals(ios_code)){
                        response.getWriter().write("{\n" +
                                "  \"meta\": {\n" +
                                "    \"success\": false,\n" +
                                "    \"message\": \"update\"\n" +
                                "  }\n" +
                                "  }");
                        return false;
                    }

            }
         }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean compare(String data1,String data2){
        boolean flag=false;
        int data1Str = Integer.valueOf(data1);
        int data2Str = Integer.valueOf(data2);
        flag = data1Str >data2Str;
        return flag;
    }
}
