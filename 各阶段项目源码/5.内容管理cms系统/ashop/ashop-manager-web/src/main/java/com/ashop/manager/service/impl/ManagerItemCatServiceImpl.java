package com.ashop.manager.service.impl;

import com.ashop.beans.TreeNode;
import com.ashop.manager.service.ManagerItemCatService;
import com.ashop.pojo.TbItemCat;
import com.ashop.rpc.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerItemCatServiceImpl implements ManagerItemCatService {

    //注入远程服务代理对象
    @Autowired
    private ItemCatService itemCatServiceProxy;

    @Override
    public List<TreeNode> getItemCatList(Long id) {
        //调用远程服务
        List<TbItemCat> list = itemCatServiceProxy.getItemCatListByParentId(id);
        //集合转换
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        TreeNode node = null;
        for (TbItemCat tic : list){
            node = new TreeNode();
            node.setId(tic.getId());
            node.setText(tic.getName());
            node.setState(tic.getIsParent() ? "closed" : "open");
            nodes.add(node);
        }
        node = null;
        return nodes;
    }
}
