package com.ashop.rpc.service;

import com.ashop.beans.AshopResult;
import com.ashop.beans.PageResult;
import com.ashop.pojo.TbItem;
import com.ashop.pojo.TbItemDesc;

import java.util.List;

public interface ItemService {
    /**
     * 实现商品信息的分页查询
     */
    public PageResult<TbItem> selectItemList(Integer page, Integer rows);

    /**
     * 完成商品上下架状态的修改
     * @param itemIds   修改的商品集合
     * @param flag  true: 上架, false: 下架
     * @return
     */
    public AshopResult updateItemStatus(List<Long> itemIds, boolean flag);

    /**
     * 删除商品信息
     * @param itemIds 需要删除的商品信息的Id集合
     * @return
     */
    public AshopResult deleteItem(List<Long> itemIds);

    /**
     * 完成商品信息的保存
     * @param item
     * @param desc
     * @return
     */
    public AshopResult saveItem(TbItem item, TbItemDesc desc);

    /**
     *
     * @param item
     * @param desc
     * @return
     */
    public AshopResult updateItem(TbItem item, TbItemDesc desc);
}
