package com.ashop.rpc.service.impl;

import com.ashop.mapper.TbItemDescMapper;
import com.ashop.pojo.TbItemDesc;
import com.ashop.rpc.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemDescServiceImpl implements ItemDescService {

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Override
    public TbItemDesc getItemDesc(Long itemId) {
        return tbItemDescMapper.selectByPrimaryKey(itemId);//通过主键查询
    }
}
