package com.ashop.rpc.service.impl;

import com.ashop.beans.AshopResult;
import com.ashop.beans.PageResult;
import com.ashop.mapper.TbItemDescMapper;
import com.ashop.mapper.TbItemMapper;
import com.ashop.mapper.TbItemParamItemMapper;
import com.ashop.pojo.*;
import com.ashop.pojo.TbItemDescExample.Criteria;
import com.ashop.rpc.service.ItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    //注入mapper接口代理对象
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

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

    @Override
    public AshopResult saveItem(TbItem item, TbItemDesc desc, TbItemParamItem tbItemParamItem) {
        tbItemMapper.insert(item);
        tbItemDescMapper.insert(desc);
        tbItemParamItemMapper.insert(tbItemParamItem);
        return AshopResult.ok();
    }

    @Override
    public AshopResult updateItem(TbItem item, TbItemDesc desc, TbItemParamItem tbItemParamItem) {
        //更新商品基本信息
        this.tbItemMapper.updateByPrimaryKeySelective(item);//Selective: 如果某个字段为空, 则不进行更新
        //查询商品的描述信息
        TbItemDescExample example = new TbItemDescExample();
        Criteria c = example.createCriteria();
        c.andItemIdEqualTo(desc.getItemId());//where itemId = ?
        Integer rows = tbItemDescMapper.countByExample(example);
        //判断该商品是否有描述信息, 有才进行修改.
        if(rows == 0){
            this.tbItemDescMapper.insert(desc);
        }else{
            desc.setCreated(null);//防止更新的时候把create创建时间也更新了.
            this.tbItemDescMapper.updateByPrimaryKeySelective(desc);//Selective: 如果某个字段为空, 则不进行更新
        }
        //更新商品的规格参数信息
        TbItemParamItemExample exam = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria ct = exam.createCriteria();
        ct.andItemIdEqualTo(tbItemParamItem.getItemId());

        tbItemParamItemMapper.updateByExampleSelective(tbItemParamItem, exam);
        return AshopResult.ok();
    }
}
