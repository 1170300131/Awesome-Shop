package com.ashop.rpc.service;

import com.ashop.pojo.TbItemParamItem;

public interface ParamItemService {
    /**
     * 根据商品id, 查询商品的规格参数信息
     * @param itemid
     * @return
     */
    public TbItemParamItem loadTbItemParamItemService(Long itemid);
}
