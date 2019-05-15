package com.ashop.manager.service;

import com.ashop.beans.AshopResult;
import com.ashop.beans.PageResult;
import com.ashop.beans.PictureResult;
import com.ashop.pojo.TbItem;
import org.springframework.web.multipart.MultipartFile;

public interface ManagerItemService {
    /**
     * 完成商品的分页查询
     */
    public PageResult<TbItem> selectItemListService(Integer page, Integer rows);

    /**
     * 完成商品上架
     */
    public AshopResult reshelfItem(Long[] ids);

    /**
     * 完成商品下架
     */
    public AshopResult instockItem(Long[] ids);

    /**
     * 删除商品信息
     * @param ids
     * @return
     */
    public AshopResult deleteItemService(Long[] ids);

    /**
     * 完成商品图片上传
     * @param file
     * @return
     */
    public PictureResult uploadItemPic(MultipartFile file);

    /**
     * 保存新增商品的信息
     * @param item 商品对象
     * @param desc 商品描述
     * @return
     */
    public AshopResult saveItemService(TbItem item, String desc, String paramData);

    /**
     * 保存商品信息更新
     * @param item 商品对象
     * @param desc 商品描述
     * @return
     */
    public AshopResult updateItemService(TbItem item, String desc, String paramData);
}
