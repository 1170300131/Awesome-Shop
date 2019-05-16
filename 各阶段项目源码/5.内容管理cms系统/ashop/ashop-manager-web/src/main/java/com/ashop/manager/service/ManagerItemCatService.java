package com.ashop.manager.service;

import com.ashop.beans.TreeNode;

import java.util.List;

public interface ManagerItemCatService {
    /**
     * 根据节点id, 加载当前节点的所有子节点集合.
     * @param id
     * @return
     */
    public List<TreeNode> getItemCatList(Long id);
}
