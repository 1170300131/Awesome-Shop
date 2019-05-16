package com.ashop.rpc.service;

import com.ashop.beans.AshopResult;
import com.ashop.pojo.TbContentCategory;

import java.util.List;

public interface TbContentCateGoryService {
    /**
     * 加载内容分类树
     * @param pId
     * @return
     */
    public List<TbContentCategory> loadTbContentCateGoryByPidService(Long pId);

    /**
     * 添加内容分类节点
     * @param tbContentCategory
     * @return
     */
    public AshopResult saveTbContentCategory(TbContentCategory tbContentCategory);

    /**
     * 删除内容分类节点
     * @param id
     */
    public void deleteTbContentCategoryService(Long id);
}
