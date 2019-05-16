package com.ashop.manager.service;

import com.ashop.beans.AshopResult;
import com.ashop.beans.PageResult;
import com.ashop.pojo.TbContent;

public interface ManagerContentService {
    /**
     * 根据内容分类的id, 进行内容的分页查询
     */
    public PageResult<TbContent> loadContentListService(Long cid, Integer page, Integer rows);

    /**
     * 完成内容的添加
     */
    public AshopResult saveContentService(TbContent tbContent);

    /**
     * 实现内容的批量删除
     */
    public AshopResult deleteContentService(String ids);

    /**
     * 实现内容的修改
     */
    public AshopResult updateContentService(TbContent tbContent);
}
