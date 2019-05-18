package com.ashop.manager.service.impl;

import com.ashop.beans.*;
import com.ashop.manager.service.ManagerItemService;
import com.ashop.pojo.TbItem;
import com.ashop.pojo.TbItemDesc;
import com.ashop.pojo.TbItemParamItem;
import com.ashop.rpc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

@Service
public class ManagerItemServiceImpl implements ManagerItemService {

    @Autowired
    private ItemService itemServiceProxy;

    //通过spring的EL表达式注入ftp信息
    @Value("${FTP_HOST}")
    private String FTP_HOST;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_PATH}")
    private String FTP_PATH;
    @Value("${IMAGE_HTTP_PATH}")
    private String IMAGE_HTTP_PATH;

    @Override
    public PageResult<TbItem> selectItemListService(Integer page, Integer rows) {
        return itemServiceProxy.selectItemList(page, rows);
    }

    @Override
    public AshopResult reshelfItem(Long[] ids) {
        ArrayList<Long> list = new ArrayList<Long>();
        for(int i = 0; i < ids.length; i++){
            list.add(ids[i]);
        }
        //调用远程服务
        return itemServiceProxy.updateItemStatus(list, true);
    }

    @Override
    public AshopResult instockItem(Long[] ids) {
        ArrayList<Long> list = new ArrayList<Long>();
        for(int i = 0; i < ids.length; i++){
            list.add(ids[i]);
        }
        return itemServiceProxy.updateItemStatus(list, false);
    }

    @Override
    public AshopResult deleteItemService(Long[] ids) {
        ArrayList<Long> list = new ArrayList<Long>();
        for(int i = 0; i < ids.length; i++){
            list.add(ids[i]);
        }
        return itemServiceProxy.deleteItem(list);
    }

    @Override
    public PictureResult uploadItemPic(MultipartFile file) {
        boolean flag = false;
        String fileName = null;
        try {
            //为图片生成一个新名字
            fileName = IDUtils.genImageName();
            //获得上传文件的原始名字
            String oriName = file.getOriginalFilename();
            String ext = oriName.substring(oriName.lastIndexOf("."));//截取图片类型后缀
            fileName = fileName + ext;//拼接到新文件名上.
            //实现文件上传
            InputStream local = file.getInputStream();
            flag = FtpUtils.uploadFile(FTP_HOST, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_PATH, fileName, local);

        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        PictureResult result = null;
        if(flag){
            result = new PictureResult();
            result.setError(0);
            result.setUrl(IMAGE_HTTP_PATH + "/" + fileName);
            result.setMessage("ok");
        }else{
            result = new PictureResult();
            result.setError(1);
            result.setUrl("url");
            result.setMessage("error");
        }
        return result;
    }

    @Override
    public AshopResult saveItemService(TbItem item, String desc, String paramData) {
        //给item对象封装数据
        Long id = IDUtils.genItemId();
        item.setId(id);//随机生成商品id, 不使用id自增, 满足以后使用分库分表的需求
        item.setStatus((byte) 1);//状态设置为1, 正常
        Date date = new Date();
        item.setCreated(date);//商品创建时间
        item.setUpdated(date);
        //创建商品描述对象tbItemDesc
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemDesc(desc);//商品的具体描述信息
        tbItemDesc.setItemId(id);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        //封装TbItemParamItem
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(id);
        itemParamItem.setParamData(paramData);
        itemParamItem.setCreated(date);
        itemParamItem.setUpdated(date);
        return itemServiceProxy.saveItem(item, tbItemDesc, itemParamItem);
    }

    @Override
    public AshopResult updateItemService(TbItem item, String desc, String paramData) {
        Date date = new Date();
        //设置商品的更新时间
        item.setUpdated(date);

        //创建TbItemDesc对象
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(item.getId());
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setUpdated(date);
        tbItemDesc.setCreated(date);

        //创建TbItemParamItem对象
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setParamData(paramData);
        tbItemParamItem.setItemId(item.getId());
        tbItemParamItem.setUpdated(date);

        return itemServiceProxy.updateItem(item, tbItemDesc, tbItemParamItem);
    }
}
