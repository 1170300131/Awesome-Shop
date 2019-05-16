package com.ashop.manager.controller;

import com.ashop.beans.AshopResult;
import com.ashop.manager.service.ManagerParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ParamItemParamController {

    @Autowired
    private ManagerParamItemService managerParamItemService;

    /**
     * 处理加载商品信息参数的请求
     */
    @ResponseBody
    @RequestMapping(value = "/param/item/query/{itemid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AshopResult paramItemQuery(@PathVariable Long itemid){
        return managerParamItemService.loadParamItemService(itemid);
    }
}
