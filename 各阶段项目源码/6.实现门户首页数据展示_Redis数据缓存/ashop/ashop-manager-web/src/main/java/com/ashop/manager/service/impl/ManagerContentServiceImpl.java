package com.ashop.manager.service.impl;

import com.ashop.beans.AshopResult;
import com.ashop.beans.PageResult;
import com.ashop.manager.service.ManagerContentService;
import com.ashop.pojo.TbContent;
import com.ashop.rpc.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ManagerContentServiceImpl implements ManagerContentService {

    //注入远程服务代理对象
    @Autowired
    private TbContentService tbContentServiceProxy;

    @Override
    public PageResult<TbContent> loadContentListService(Long cid, Integer page, Integer rows) {
        return tbContentServiceProxy.loadTbContentListService(cid, page, rows);
    }

    @Override
    public AshopResult saveContentService(TbContent tbContent) {
        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        return tbContentServiceProxy.saveTbContentService(tbContent);
    }

    @Override
    public AshopResult deleteContentService(String ids) {
        String[] idArr = ids.trim().split(",");
        List<Long> list = new ArrayList<>();
        for(String s : idArr){
            list.add(Long.parseLong(s));
        }
        return tbContentServiceProxy.deleteTbContentService(list);
    }

    @Override
    public AshopResult updateContentService(TbContent tbContent) {
        Date date = new Date();
        tbContent.setUpdated(date);
        return tbContentServiceProxy.updateTbContentService(tbContent);
    }
}
