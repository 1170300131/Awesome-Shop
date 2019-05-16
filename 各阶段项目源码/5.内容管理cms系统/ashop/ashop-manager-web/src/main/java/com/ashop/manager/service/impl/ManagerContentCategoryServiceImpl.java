package com.ashop.manager.service.impl;

import com.ashop.beans.AshopResult;
import com.ashop.beans.IDUtils;
import com.ashop.beans.TreeNode;
import com.ashop.manager.service.ManagerContentCategoryService;
import com.ashop.pojo.TbContentCategory;
import com.ashop.rpc.service.TbContentCateGoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ManagerContentCategoryServiceImpl implements ManagerContentCategoryService {

    @Autowired
    private TbContentCateGoryService tbContentCateGoryServiceProxy;

    @Override
    public List<TreeNode> loadContentCategoryService(Long pid) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        List<TbContentCategory> clist = tbContentCateGoryServiceProxy.loadTbContentCateGoryByPidService(pid);
        for(TbContentCategory c : clist){
            //创建TreeNode
            TreeNode node = new TreeNode();
            node.setId(c.getId());
            node.setText(c.getName());
            node.setState(c.getIsParent() ? "closed" : "open");//如果是父结点就不展开, 子节点则展开.
            list.add(node);
        }
        return list;
    }

    @Override
    public AshopResult saveContentCategoryService(TbContentCategory contentCategory) {
        Date date = new Date();
        //生成id
        Long id = IDUtils.genItemId();
        contentCategory.setId(id);
        contentCategory.setCreated(date);
        contentCategory.setUpdated(date);
        contentCategory.setStatus(1);
        contentCategory.setSortOrder(1);
        contentCategory.setIsParent(false);

        return tbContentCateGoryServiceProxy.saveTbContentCategory(contentCategory);
    }

    @Override
    public void deleteContentCategoryService(Long id) {
        tbContentCateGoryServiceProxy.deleteTbContentCategoryService(id);
    }
}
