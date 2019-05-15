package com.ashop.rpc.service.impl;

import com.ashop.mapper.TbItemParamItemMapper;
import com.ashop.pojo.TbItemParamItem;
import com.ashop.pojo.TbItemParamItemExample;
import com.ashop.pojo.TbItemParamItemExample.Criteria;
import com.ashop.rpc.service.ParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParamItemServiceImpl implements ParamItemService {

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    public TbItemParamItem loadTbItemParamItemService(Long id) {
        TbItemParamItemExample example = new TbItemParamItemExample();
        Criteria c = example.createCriteria();
        //封装查询条件
        c.andItemIdEqualTo(id);
        List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        if(null != list && list.size() == 1){
            return list.get(0);
        }
        return null;
    }
}
