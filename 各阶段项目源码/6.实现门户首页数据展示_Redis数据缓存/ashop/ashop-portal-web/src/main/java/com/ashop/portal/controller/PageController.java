package com.ashop.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    /**
     * 进行页面跳转
     */
    @RequestMapping("/{url}")
    public String loadPage(@PathVariable String url){
        return url;
    }

}
