package com.czt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.czt.pojo.Content;
import com.czt.service.ContentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.controller
 *  @文件名:   ContentController
 *  @创建者:   Administrator
 *  @创建时间:  2018/9/29 14:49
 *  @描述：    TODO
 */
@RestController
public class ContentController {


    @Reference
    private ContentService contentService;

    @RequestMapping("/rest/content")
    public  String add(Content content){

        contentService.add(content);

        return  "success!!";


    }
}
