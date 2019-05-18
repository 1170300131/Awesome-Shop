package com.ashop.portal.service.impl;

import com.ashop.beans.BigPicture;
import com.ashop.beans.JsonUtils;
import com.ashop.pojo.TbContent;
import com.ashop.portal.service.PortalContentService;
import com.ashop.portal.service.PortalItemCatService;
import com.ashop.rpc.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortalContentServiceImpl implements PortalContentService {

    @Value("${CONTENT_PICTURE}")
    private String contentPictureKey;

    //注入JedisCluster对象
    @Autowired
    private JedisCluster cluster;

    @Autowired
    private TbContentService tbContentServiceProxy;

    @Override
    public String loadContentListByCidService(Long cid) {
        //查询redis数据库
        String jsonStr = cluster.get(contentPictureKey);
        if(!StringUtils.isEmpty(jsonStr)){
            return jsonStr;
        }
        //调用远程服务
        List<TbContent> list = tbContentServiceProxy.loadTbContentListByCidService(cid);
        //封装符合前台数据格式的广告数据
        List<BigPicture> bigList = new ArrayList<BigPicture>();
        for (TbContent content : list){
            BigPicture pic = new BigPicture();
            pic.setSrcb(content.getPic());
            pic.setHeight(240);
            pic.setAlt(content.getTitle());
            pic.setWidth(670);
            pic.setSrc(content.getPic2());
            pic.setWidthb(550);
            pic.setHref(content.getUrl());
            pic.setHeightb(240);
            bigList.add(pic);
        }
        //将符合前台数据规范的list集合, 序列化为json字符串
        String str = JsonUtils.objectToJson(bigList);
        //将str保存到redis数据库
        cluster.set(contentPictureKey, str);
        //设置key的生命周期为一天
        cluster.expire(contentPictureKey, 86400);
        return str;
    }
}
