package com.bossien.controller;

import com.bossien.common.bean.Response;
import com.bossien.common.util.PropertiesUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 通过页面修改properties属性值
 */
@Controller
@RequestMapping("/api/v1.0/properties")
public class PropertiesController {

    @RequestMapping("/list")
    public ModelAndView index() {
        ModelAndView mv=new ModelAndView();
        Map<String, String> pmap = PropertiesUtils.getAllProperties();
        mv.addObject("pmap",pmap);
        mv.setViewName("/console/properties_list");
        return mv;
    }


    @RequestMapping(value = "/modify")
    public ModelAndView modifyPassWord(HttpServletRequest request, String pkey,String pvalue){
        ModelAndView mv = new ModelAndView();
        mv.addObject("pkey",pkey);
        mv.addObject("pvalue",pvalue);
        mv.setViewName("/console/modify_properties");
        return mv;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Response updatePass(String pkey, String pvalue) {
        Response resp=new Response();
        if (StringUtils.isEmpty(pkey) || StringUtils.isEmpty(pvalue)) {
            resp.failure("100000");
            return resp;
        }
        Map<String, String> map=new HashMap<String, String>();
        map.put(pkey,pvalue);
        PropertiesUtils.setValue(map);
        resp.success("000000");
        return resp;
    }
}
