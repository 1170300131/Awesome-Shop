package com.ashop.manager.controller;

import com.ashop.beans.AshopResult;
import com.ashop.beans.TreeNode;
import com.ashop.manager.service.ManagerContentCategoryService;
import com.ashop.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ManagerContentCategoryController {

    @Autowired
    private ManagerContentCategoryService managerContentCategoryService;

    /**
     * 处理加载内容分类树的请求.
     */
    @ResponseBody
    @RequestMapping(value = "/content/category/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<TreeNode> contentCategoryList(@RequestParam(defaultValue = "0") Long id){
        return managerContentCategoryService.loadContentCategoryService(id);
    }

    /**
     * 处理添加内容分类节点的请求.
     */
    @ResponseBody
    @RequestMapping(value = "/content/category/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AshopResult contentCategoryCreate(TbContentCategory contentCategory){
        return managerContentCategoryService.saveContentCategoryService(contentCategory);
    }

    /**
     * 处理内容分类节点的删除请求
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/content/category/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AshopResult contentCategoryDelete(Long id){
        managerContentCategoryService.deleteContentCategoryService(id);
        return null;
    }
}
