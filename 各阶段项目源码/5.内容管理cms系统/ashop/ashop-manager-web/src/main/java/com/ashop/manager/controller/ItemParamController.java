package com.ashop.manager.controller;

import com.ashop.beans.AshopResult;
import com.ashop.beans.PageResult;
import com.ashop.manager.service.ManagerItemParamService;
import com.ashop.pojo.TbItemParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemParamController {

    //注入service对象
    @Autowired
    private ManagerItemParamService managerItemParamService;

    /**
     * 处理规格参数模板分页查询的请求
     */
    @ResponseBody
    @RequestMapping(value = "/item/param/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PageResult<TbItemParam> itemParamList(@RequestParam(defaultValue = "1") Integer page,
                                                 @RequestParam(defaultValue = "30") Integer rows){
        return managerItemParamService.loadItemParamListService(page, rows);
    }

    /**
     * 处理根据商品类目id, 查询规格模板请求.
     */
    @ResponseBody
    @RequestMapping(value = "/item/param/query/{cid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AshopResult itemParamQuery(@PathVariable Long cid){
        return managerItemParamService.loadItemParamByCidService(cid);
    }

    /**
     * 处理新增商品参数模板信息的请求
     */
    @ResponseBody
    @RequestMapping(value = "/item/param/save/{cid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AshopResult saveItemParam(@PathVariable Long cid, String paramData){
        return managerItemParamService.saveItemParamService(cid, paramData);
    }

    /**
     * 根据商品类目id集合批量删除商品参数模板信息
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/item/param/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AshopResult deleteItemParam(Long[] ids){
        return managerItemParamService.deleteItemParamService(ids);
    }

    /**
     * 处理根据商品类目id, 查询规格模板请求.
     */
    @ResponseBody
    @RequestMapping(value = "/item/param/query/itemcatid/{cid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AshopResult itemParamSelect(@PathVariable Long cid){
        return managerItemParamService.loadItemParamByCidService(cid);
    }
}
