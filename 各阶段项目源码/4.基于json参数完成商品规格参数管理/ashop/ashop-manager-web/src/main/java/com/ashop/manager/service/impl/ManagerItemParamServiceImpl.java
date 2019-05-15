package com.ashop.manager.service.impl;

import com.ashop.beans.AshopResult;
import com.ashop.beans.PageResult;
import com.ashop.manager.service.ManagerItemParamService;
import com.ashop.pojo.TbItemParam;
import com.ashop.rpc.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ManagerItemParamServiceImpl implements ManagerItemParamService {

    //注入服务的远程代理对象
    @Autowired
    private ItemParamService itemParamServiceProxy;

    @Override
    public PageResult<TbItemParam> loadItemParamListService(Integer page, Integer rows) {

        return itemParamServiceProxy.loadTbItemParamListService(page, rows);
    }

    @Override
    public AshopResult loadItemParamByCidService(Long cid) {
        AshopResult result = null;
        try{
            TbItemParam tbItemParam = itemParamServiceProxy.loadTbItemParamByCidService(cid);
            result = new AshopResult();
            result.setStatus(200);
            result.setData(tbItemParam);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public AshopResult saveItemParamService(Long cid, String paramDate) {
        TbItemParam tbItemParam = new TbItemParam();
        //封装参数
        tbItemParam.setItemCatId(cid);
        tbItemParam.setParamData(paramDate);
        Date date = new Date();
        tbItemParam.setCreated(date);
        tbItemParam.setUpdated(date);
        return itemParamServiceProxy.saveTbItemParamService(tbItemParam);
    }

    @Override
    public AshopResult deleteItemParamService(Long[] ids) {
        //将Long类型数组转化为List集合
        List<Long>  asList = Arrays.asList(ids);
        return itemParamServiceProxy.deleteTbItemParamService(asList);
    }
}
