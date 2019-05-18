package com.ashop.manager.controller;

import com.ashop.beans.TreeNode;
import com.ashop.manager.service.ManagerItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemCatController {

    @Autowired
    private ManagerItemCatService managerItemCatService;

    /**
     * 处理加载商品类目的请求
     */
    @RequestMapping(value = "item/cat/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<TreeNode> itemCatList(@RequestParam(defaultValue = "0", required = false) Long id){
        return managerItemCatService.getItemCatList(id);
    }


}
