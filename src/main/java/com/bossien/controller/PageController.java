package com.bossien.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class PageController {

    @RequestMapping(value = "/common/header", method= RequestMethod.GET)
    public ModelAndView common_header()
    {
        return new ModelAndView("/common/header");
    }

    @RequestMapping(value = "/common/script", method= RequestMethod.GET)
    public ModelAndView common_script()
    {
        return new ModelAndView("/common/script");
    }
    
    @RequestMapping(value = "/common/style", method= RequestMethod.GET)
    public ModelAndView common_style()
    {
        return new ModelAndView("/common/style");
    }

    @RequestMapping(value = "/common/footer", method= RequestMethod.GET)
    public ModelAndView common_footer()
    {
        return new ModelAndView("/common/footer");
    }


}
