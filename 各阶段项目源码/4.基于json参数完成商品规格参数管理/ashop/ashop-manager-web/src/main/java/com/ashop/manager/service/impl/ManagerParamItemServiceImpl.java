package com.ashop.manager.service.impl;

import com.ashop.beans.AshopResult;
import com.ashop.manager.service.ManagerParamItemService;
import com.ashop.pojo.TbItemParamItem;
import com.ashop.rpc.service.ParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerParamItemServiceImpl implements ManagerParamItemService {

    @Autowired
    private ParamItemService paramItemServiceProxy;

    @Override
    public AshopResult loadParamItemService(Long itemid) {
        AshopResult result = null;
        try{
            TbItemParamItem tbItemParamItem = paramItemServiceProxy.loadTbItemParamItemService(itemid);
            result = new AshopResult();
            result.setData(tbItemParamItem);
            result.setStatus(200);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
