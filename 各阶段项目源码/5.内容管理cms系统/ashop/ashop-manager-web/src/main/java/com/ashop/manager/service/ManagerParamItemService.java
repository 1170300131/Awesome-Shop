package com.ashop.manager.service;

import com.ashop.beans.AshopResult;

public interface ManagerParamItemService {
    /**
     * 根据商品id加载商品的规格信息
     * @param itemid
     * @return
     */
    public AshopResult loadParamItemService(Long itemid);
}
