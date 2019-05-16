package com.ashop.rpc.service;

import com.ashop.beans.AshopResult;
import com.ashop.beans.PageResult;
import com.ashop.pojo.TbContent;

import java.util.List;

public interface TbContentService {

    /**
     * 根据内容分类的id, 查询该分类下的所有内容
     * @param cid
     * @return
     */
    public PageResult<TbContent> loadTbContentListService(Long cid, Integer page, Integer rows);

    /**
     * 完成广告内容的添加
     * @param tbContent
     * @return
     */
    public AshopResult saveTbContentService(TbContent tbContent);

    /**
     * 完成内容的批量删除
     * @param ids
     * @return
     */
    public AshopResult deleteTbContentService(List<Long> ids);

    /**
     * 完成内容的更新
     * @return
     */
    public AshopResult updateTbContentService(TbContent tbContent);
}
