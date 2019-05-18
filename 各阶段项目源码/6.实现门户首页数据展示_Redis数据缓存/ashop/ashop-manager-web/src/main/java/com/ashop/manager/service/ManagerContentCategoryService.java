package com.ashop.manager.service;

import com.ashop.beans.AshopResult;
import com.ashop.beans.TreeNode;
import com.ashop.pojo.TbContentCategory;

import java.util.List;

public interface ManagerContentCategoryService {
    /**
     * 加载内容分类树
     * @param pid
     * @return
     */
    public List<TreeNode> loadContentCategoryService(Long pid);

    /**
     * 添加内容分类节点
     * @param contentCategory
     * @return
     */
    public AshopResult saveContentCategoryService(TbContentCategory contentCategory);

    /**
     * 删除内容分类节点
     * @param id
     */
    public void deleteContentCategoryService(Long id);
}
