package com.ashop.portal.controller;

import com.ashop.portal.service.PortalItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PortalItemCatController {

    @Autowired
    private PortalItemCatService portalItemCatService;

    /**
     * 处理加载商品类目的请求
     */
    @RequestMapping(value = "/item/cat", produces = MediaType.TEXT_HTML_VALUE + ";charset=UTF-8")
    @ResponseBody
    public String itemCat(){
        return portalItemCatService.loadItemCatService();
    }


}
