package com.ashop.rpc.service.impl;

import com.ashop.mapper.TbItemCatMapper;
import com.ashop.pojo.TbItemCat;
import com.ashop.pojo.TbItemCatExample;
import com.ashop.pojo.TbItemCatExample.*;
import com.ashop.rpc.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    //注入Mapper接口代理对象
    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<TbItemCat> getItemCatListByParentId(Long id) {
        TbItemCatExample example = new TbItemCatExample();
        Criteria c = example.createCriteria();
        //where parent_id = ?
        c.andParentIdEqualTo(id);
        List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
        return list;
    }
}
