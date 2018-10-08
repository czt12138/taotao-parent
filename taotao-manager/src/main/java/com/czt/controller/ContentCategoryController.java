package com.czt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.czt.pojo.ContentCategory;
import com.czt.service.ContentCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.controller
 *  @文件名:   ContentCategoryController
 *  @创建者:   Administrator
 *  @创建时间:  2018/9/28 8:45
 *  @描述：    TODO
 */
@Controller
public class ContentCategoryController {

    @Reference
    private ContentCategoryService service;

    @RequestMapping("/rest/content/category")
    @ResponseBody
    public  List<ContentCategory> getCategoryByParentId(@RequestParam(defaultValue = "0") Long id){


        List<ContentCategory> list = service.getCategoryByParentId(id);

        return  list;


    }

    @RequestMapping("/rest/content/category/add")
    @ResponseBody
    public  ContentCategory add(ContentCategory contentCategory){

        contentCategory  = service.add(contentCategory);


        return  contentCategory;

    }

    @RequestMapping("/rest/content/category/update")
    @ResponseBody
    public  String update(ContentCategory contentCategory){ // id,name

        service.update(contentCategory);

        return  "success!!";

    }

    @RequestMapping("/rest/content/category/delete")
    @ResponseBody
    public  String delete(ContentCategory contentCategory){ // id,parentId

        int result = service.delete(contentCategory);

        System.out.println("result=" + result);

        return  "success!!";

    }




}
