package com.ashop.rpc.service;

import com.ashop.beans.AshopResult;
import com.ashop.beans.PageResult;
import com.ashop.pojo.TbItemParam;

import java.util.List;

public interface ItemParamService {
    /**
     * 商品规格参数模板的规格显示
     */
    public PageResult<TbItemParam> loadTbItemParamListService(Integer page, Integer row);

    /**
     * 根据商品类名的id, 获得该类名的规格参数模板对象
     * @param cid
     * @return
     */
    public TbItemParam loadTbItemParamByCidService(Long cid);

    /**
     * 添加商品规格参数模板信息
     */
    public AshopResult saveTbItemParamService(TbItemParam tbItemParam);

    /**
     * 批量删除商品模板信息
     * @param ids
     * @return
     */
    public AshopResult deleteTbItemParamService(List<Long> ids);
}
