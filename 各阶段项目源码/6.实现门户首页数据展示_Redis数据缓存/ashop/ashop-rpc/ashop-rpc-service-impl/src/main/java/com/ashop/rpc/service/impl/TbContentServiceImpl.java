package com.ashop.rpc.service.impl;

import com.ashop.beans.AshopResult;
import com.ashop.beans.PageResult;
import com.ashop.mapper.TbContentMapper;
import com.ashop.pojo.TbContent;
import com.ashop.pojo.TbContentExample;
import com.ashop.rpc.service.TbContentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Override
    public PageResult<TbContent> loadTbContentListService(Long cid, Integer page, Integer rows) {
        try{
            PageResult<TbContent> result = new PageResult<>();
            //指定分页查询参数
            Page pe = PageHelper.startPage(page, rows);
            //创建TbContentExample对象
            TbContentExample example = new TbContentExample();
            TbContentExample.Criteria c = example.createCriteria();
            c.andCategoryIdEqualTo(cid);
            //查询数据库
            List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);

            result.setRows(list);
            result.setTotal(pe.getTotal());
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AshopResult saveTbContentService(TbContent tbContent) {
        try{
            tbContentMapper.insert(tbContent);
            return AshopResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AshopResult deleteTbContentService(List<Long> ids) {
        try{
            TbContentExample example = new TbContentExample();
            TbContentExample.Criteria c = example.createCriteria();
            c.andIdIn(ids);//where id in ()
            tbContentMapper.deleteByExample(example);
            return AshopResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AshopResult updateTbContentService(TbContent tbContent) {
        try{
            tbContentMapper.updateByPrimaryKeySelective(tbContent);
            return AshopResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TbContent> loadTbContentListByCidService(Long cid) {
        try{
            TbContentExample example = new TbContentExample();
            TbContentExample.Criteria c = example.createCriteria();
            c.andCategoryIdEqualTo(cid);//where id = ...
            List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
