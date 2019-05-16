package com.ashop.manager.service.impl;

import com.ashop.beans.AshopResult;
import com.ashop.manager.service.ManagerItemDescService;
import com.ashop.pojo.TbItemDesc;
import com.ashop.rpc.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerItemDescServiceImpl implements ManagerItemDescService {

    @Autowired
    private ItemDescService itemDescServiceProxy;

    @Override
    public AshopResult getItemDescService(Long itemId) {
        //调用远程服务
        TbItemDesc desc = itemDescServiceProxy.getItemDesc(itemId);
        return null != desc ? new AshopResult(desc) : new AshopResult(null);
    }
}
