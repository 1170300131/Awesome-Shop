package com.ashop.manager.service;

import com.ashop.beans.AshopResult;
import com.ashop.beans.PageResult;
import com.ashop.pojo.TbItemParam;

public interface ManagerItemParamService {
    /**
     * 实现商品规格参数的分页查询
     * @param page
     * @param rows
     * @return
     */
    public PageResult<TbItemParam> loadItemParamListService(Integer page, Integer rows);

    /**
     * 根据商品类目id, 查询商品类目对应的规格参数模板.
     * @param cid
     * @return
     */
    public AshopResult loadItemParamByCidService(Long cid);

    /**
     * 根据商品类目id, 保存商品的规格参数模板信息
     * @param id 商品的类目id
     * @param paramDate 商品的规格参数模板信息
     * @return
     */
    public AshopResult saveItemParamService(Long id, String paramDate);

    /**
     * 进行商品信息模板的批量删除
     * @param ids
     * @return
     */
    public AshopResult deleteItemParamService(Long[] ids);
}
