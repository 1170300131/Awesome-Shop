package com.ashop.rpc.service;

import com.ashop.pojo.TbItemCat;

import java.util.List;

public interface ItemCatService {
    /**
     * 根据某个节点的id, 查询子节点集合
     * @param id    当前节点id
     * @return
     */
    public List<TbItemCat> getItemCatListByParentId(Long id);

    /**
     * 加载门户首页的商品类目
     */
    public List<TbItemCat> loadItemCatListService();
}
