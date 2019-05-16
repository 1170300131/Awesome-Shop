package com.ashop.rpc.service.impl;

import com.ashop.beans.AshopResult;
import com.ashop.beans.PageResult;
import com.ashop.mapper.TbItemParamMapper;
import com.ashop.pojo.TbItemParam;
import com.ashop.pojo.TbItemParamExample;
import com.ashop.pojo.TbItemParamExample.Criteria;
import com.ashop.rpc.service.ItemParamService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    @Override
    public PageResult<TbItemParam> loadTbItemParamListService(Integer page, Integer row) {
        PageResult<TbItemParam> pageResult = new PageResult<TbItemParam>();
        //指定分页参数
        Page p = PageHelper.startPage(page, row);

        TbItemParamExample example = new TbItemParamExample();
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);//表中类型为Text的(保存大量内容)的字段
        pageResult.setRows(list);
        pageResult.setTotal(p.getTotal());
        return pageResult;
    }

    @Override
    public TbItemParam loadTbItemParamByCidService(Long cid) {
        TbItemParamExample example = new TbItemParamExample();
        //封装查询条件
        Criteria c = example.createCriteria();
        c.andItemCatIdEqualTo(cid);//根据商品的类名id字段来查

        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
        if(null != list && list.size() == 1){
            return list.get(0);
        }
        return null;
    }

    @Override
    public AshopResult saveTbItemParamService(TbItemParam tbItemParam) {
        try{
            tbItemParamMapper.insert(tbItemParam);
            return AshopResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AshopResult deleteTbItemParamService(List<Long> ids) {
        try{
            //创建Example对象
            TbItemParamExample example = new TbItemParamExample();
            Criteria c = example.createCriteria();
            //封装删除条件
            c.andIdIn(ids);
            tbItemParamMapper.deleteByExample(example);
            return AshopResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
