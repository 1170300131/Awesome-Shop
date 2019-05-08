package com.ashop.manager.service;

import com.ashop.beans.AshopResult;
import com.ashop.beans.PageResult;
import com.ashop.pojo.TbItem;

public interface ManagerItemService {
    /**
     * 完成商品的分页查询
     */
    public PageResult<TbItem> selectItemListService(Integer page, Integer rows);

    /**
     * 完成商品上架
     */
    public AshopResult reshelfItem(Long[] ids);

    /**
     * 完成商品下架
     */
    public AshopResult instockItem(Long[] ids);

    /**
     * 删除商品信息
     * @param ids
     * @return
     */
    public AshopResult deleteItemService(Long[] ids);

}
