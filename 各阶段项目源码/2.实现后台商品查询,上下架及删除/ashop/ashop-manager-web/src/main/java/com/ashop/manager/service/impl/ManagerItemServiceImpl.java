package com.ashop.manager.service.impl;

import com.ashop.beans.AshopResult;
import com.ashop.beans.PageResult;
import com.ashop.manager.service.ManagerItemService;
import com.ashop.pojo.TbItem;
import com.ashop.rpc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ManagerItemServiceImpl implements ManagerItemService {

    @Autowired
    private ItemService itemServiceProxy;

    @Override
    public PageResult<TbItem> selectItemListService(Integer page, Integer rows) {
        return itemServiceProxy.selectItemList(page, rows);
    }

    @Override
    public AshopResult reshelfItem(Long[] ids) {
        ArrayList<Long> list = new ArrayList<>();
        for(int i = 0; i < ids.length; i++){
            list.add(ids[i]);
        }
        //调用远程服务
        return itemServiceProxy.updateItemStatus(list, true);
    }

    @Override
    public AshopResult instockItem(Long[] ids) {
        ArrayList<Long> list = new ArrayList<>();
        for(int i = 0; i < ids.length; i++){
            list.add(ids[i]);
        }
        return itemServiceProxy.updateItemStatus(list, false);
    }

    @Override
    public AshopResult deleteItemService(Long[] ids) {
        ArrayList<Long> list = new ArrayList<>();
        for(int i = 0; i < ids.length; i++){
            list.add(ids[i]);
        }
        return itemServiceProxy.deleteItem(list);
    }
}
