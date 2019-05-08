package com.ashop.rpc.service.impl;

import com.ashop.beans.AshopResult;
import com.ashop.beans.PageResult;
import com.ashop.mapper.TbItemMapper;
import com.ashop.pojo.TbItem;
import com.ashop.pojo.TbItemExample;
import com.ashop.rpc.service.ItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    //注入mapper接口代理对象
    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public PageResult<TbItem> selectItemList(Integer page, Integer rows) {
        //执行分页操作
        Page ps = PageHelper.startPage(page, rows);
        //创建example对象
        TbItemExample example = new TbItemExample();
        //执行数据库查询操作
        List<TbItem> list = tbItemMapper.selectByExample(example);
        PageResult<TbItem> result = new PageResult<>();
        result.setRows(list);
        result.setTotal(ps.getTotal());
        return result;
    }

    @Override
    public AshopResult updateItemStatus(List<Long> itemIds, boolean flag) {
        //创建TbItem对象
        TbItem item = new TbItem();
        if(flag){
            item.setStatus((byte) 1);
        }else{
            item.setStatus((byte) 2);
        }
        //动态产生where条件
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria c = example.createCriteria();
        c.andIdIn(itemIds);//产生这样的效果: where id in(3, 4, 5...)
        tbItemMapper.updateByExampleSelective(item, example);
        return AshopResult.ok();
    }

    @Override
    public AshopResult deleteItem(List<Long> itemIds) {
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria c = example.createCriteria();
        c.andIdIn(itemIds);
        tbItemMapper.deleteByExample(example);
        return new AshopResult().ok();
    }
}
