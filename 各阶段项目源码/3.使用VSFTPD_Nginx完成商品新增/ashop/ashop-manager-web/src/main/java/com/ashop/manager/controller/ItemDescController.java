package com.ashop.manager.controller;

import com.ashop.beans.AshopResult;
import com.ashop.manager.service.ManagerItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemDescController {
    @Autowired
    private ManagerItemDescService managerItemDescService;

    /**
     * 处理加载商品描述信息的请求
     * @param itemId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "query/item/desc/{itemId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AshopResult itemDesc(@PathVariable Long itemId){
        return managerItemDescService.getItemDescService(itemId);
    }
}
