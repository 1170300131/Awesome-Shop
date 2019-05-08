package com.ashop.manager.controller;

import com.ashop.beans.AshopResult;
import com.ashop.beans.PageResult;
import com.ashop.manager.service.ManagerItemService;
import com.ashop.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {
    @Autowired
    private ManagerItemService managerItemService;

    /**
     * 处理商品信息分页查询请求
     */
    @ResponseBody
    @RequestMapping(value = "item/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PageResult<TbItem> itemList(@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "30") Integer rows){
        return managerItemService.selectItemListService(page, rows);
    }

    /**
     * 处理商品上架请求
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "item/reshelf", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AshopResult reshelfItem(Long[] ids){
        return managerItemService.reshelfItem(ids);
    }

    /**
     * 处理商品下架请求
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "item/instock", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AshopResult instockItem(Long[] ids){
        return managerItemService.instockItem(ids);
    }

    /**
     * 处理删除商品信息的请求
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "item/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AshopResult deleteItem(Long[] ids){
        return managerItemService.deleteItemService(ids);
    }
}
