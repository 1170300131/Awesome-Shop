package com.ashop.rpc.service.impl;

import com.ashop.beans.AshopResult;
import com.ashop.mapper.TbContentCategoryMapper;
import com.ashop.pojo.TbContentCategory;
import com.ashop.pojo.TbContentCategoryExample;
import com.ashop.rpc.service.TbContentCateGoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbContentCateGoryServiceImpl implements TbContentCateGoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<TbContentCategory> loadTbContentCateGoryByPidService(Long pId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria c = example.createCriteria();
        c.andParentIdEqualTo(pId);//where parent_id = ?
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
        return list;
    }

    @Override
    public AshopResult saveTbContentCategory(TbContentCategory tbContentCategory) {
        AshopResult result = null;
        try{
            //创建一个新增节点的父结点对象
            TbContentCategory record = new TbContentCategory();
            record.setIsParent(true);
            record.setId(tbContentCategory.getParentId());
            //更新 新增节点的父结点的is_parent字段
            tbContentCategoryMapper.updateByPrimaryKeySelective(record);
            //添加内容分类节点
            tbContentCategoryMapper.insert(tbContentCategory);
            result = new AshopResult();
            result.setStatus(200);
            result.setData(tbContentCategory);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void deleteTbContentCategoryService(Long id) {
        //更新当前删除节点的父节点的is_parent字段
        //获得当前节点的父节点id
        TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
        Long pid = tbContentCategory.getParentId();
        //根据pid查询该父结点有多少个子节点
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria c = example.createCriteria();
        c.andParentIdEqualTo(pid);//where parend_id = ?
        int count = tbContentCategoryMapper.countByExample(example);
        if(count == 1){
            //更新该父结点的is_parent字段
            TbContentCategory tcc = new TbContentCategory();
            tcc.setIsParent(false);
            tcc.setId(pid);
            tbContentCategoryMapper.updateByPrimaryKeySelective(tcc);
        }
        //删除当前点击的节点
        tbContentCategoryMapper.deleteByPrimaryKey(id);
    }
}
