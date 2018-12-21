package com.czt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.czt.pojo.ItemCat;
import com.czt.service.ItemCatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.controller
 *  @文件名:   ItemCatController
 *  @创建者:   czt
 *  @创建时间:  2018/9/25 10:16
 *  @描述：   商品分类控制器
 */
@Controller
public class ItemCatController {

    @Reference
    private ItemCatService itemCatService;

    @RequestMapping("/rest/item/cat")
    @ResponseBody
        public List<ItemCat> getCategoryByParentId(@RequestParam(defaultValue = "0") long id) {

        //默认先获取所有的一级分类
        List<ItemCat> list = itemCatService.getCategoryByParentId(id);

        System.out.println("list==" + list);

        return  list;

        }

}
