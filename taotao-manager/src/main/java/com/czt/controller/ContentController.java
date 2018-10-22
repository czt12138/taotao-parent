package com.czt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.czt.pojo.Content;
import com.czt.service.ContentService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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

    //categoryId=91&page=1&rows=20
    @GetMapping("/rest/content")
    public Map<String , Object> list(long categoryId , int page , int rows){

        PageInfo<Content> pageInfo = contentService.list(categoryId, page, rows);
        /*
            bushi easyUI 显示列表数据：
            json格式的数据

            total:200
            rows: [{},{},{}]
         */

        Map<String , Object> map  = new HashMap<String ,Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());

        return map;
    }

    @RequestMapping("/rest/content/edit")
    public  Map<String , Object> edit(Content content){

        int result = contentService.edit(content);

        //System.out.println("result=" + result);

        Map<String , Object> map = new HashMap<>();

        if(result > 0){
            map.put("status",200);
        }else{
            map.put("status",500);
        }


        return map;
    }


    @RequestMapping("/rest/content/delete")
    public  Map<String , Object> delete(String ids){//ids: 97,98  | ids:97


        int result = contentService.delete(ids);


        Map<String , Object> map = new HashMap<>();

        if(result > 0){
            map.put("status",200);
        }else{
            map.put("status",500);
        }

        return map;
    }
}
