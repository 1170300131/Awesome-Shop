package com.ashop.manager.service;

import com.ashop.beans.AshopResult;

public interface ManagerItemDescService {
    /**
     * 获得需要回显的商品描述信息
     * @param itemId
     * @return
     */
    public AshopResult getItemDescService(Long itemId);
}
