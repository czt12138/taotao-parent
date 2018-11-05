package com.czt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.czt.service.ContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.controller
 *  @文件名:   IndexController
 *  @创建者:   Administrator
 *  @创建时间:  2018/9/27 15:44
 *  @描述：    TODO
 */
@Controller
public class IndexController {

    @Reference
    private ContentService contentService;


    @RequestMapping("/")
    public  String index(Model model){

        int categoryId=99;

        String json = contentService.selectByCategoryId(categoryId);

        model.addAttribute("list",json);

        return  "index";
    }

}

