package com.ashop.manager.controller;

import com.ashop.beans.AshopResult;
import com.ashop.beans.PageResult;
import com.ashop.manager.service.ManagerContentService;
import com.ashop.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ManagerContentController {

    @Autowired
    private ManagerContentService managerContentService;

    /**
     * 处理内容分页查询请求
     */
    @ResponseBody
    @RequestMapping(value = "/content/query/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PageResult<TbContent> contentCategoryDelete(Long categoryId, Integer page, Integer rows){
        return managerContentService.loadContentListService(categoryId, page, rows);
    }

    /**
     * 处理内容的添加请求
     */
    @ResponseBody
    @RequestMapping(value = "/content/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AshopResult contentSave(TbContent tbContent){
        return managerContentService.saveContentService(tbContent);
    }

    /**
     * 处理批量删除内容的添加请求
     */
    @ResponseBody
    @RequestMapping(value = "/content/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AshopResult contentDelete(String ids){
        return managerContentService.deleteContentService(ids);
    }

    /**
     * 处理更新内容的请求
     */
    @ResponseBody
    @RequestMapping(value = "/rest/content/edit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AshopResult contentDelete(TbContent tbContent){
        return managerContentService.updateContentService(tbContent);
    }
}
