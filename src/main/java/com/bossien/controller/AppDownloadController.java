package com.bossien.controller;

import com.bossien.common.util.PropertiesUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 手机微信中引导下载app控制器
 */
@Controller
@RequestMapping("/api/v1.0/download")
public class AppDownloadController {

    @RequestMapping("/loading")
    public ModelAndView index() {
        ModelAndView mv=new ModelAndView();
        //android下载地址
        String androidDownloadUrl= PropertiesUtils.getValue("update_version_url");
        //ios下载地址
        String iosDownloadUrl=PropertiesUtils.getValue("ios_download_url");
        mv.addObject("androidDownloadUrl",androidDownloadUrl);
        mv.addObject("iosDownloadUrl",iosDownloadUrl);
        mv.setViewName("/download/app_download");
        return mv;
    }



}
