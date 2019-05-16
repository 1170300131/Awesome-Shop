package com.ashop.manager.controller;

import com.ashop.beans.PictureResult;
import com.ashop.manager.service.ManagerItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ItemImageController {

    //注入service对象
    @Autowired
    private ManagerItemService managerItemService;

    /**
     * 处理图片上传请求
     */
    @RequestMapping(value = "pic/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public PictureResult picUpload(MultipartFile uploadFile){
        return managerItemService.uploadItemPic(uploadFile);
    }
}
