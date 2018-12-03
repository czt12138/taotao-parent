package com.czt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.czt.pojo.Item;
import com.czt.pojo.ItemDesc;
import com.czt.service.ItemDescService;
import com.czt.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.controller
 *  @文件名:   ItemController
 *  @创建者:   Czt
 *  @创建时间:  2018/12/3 10:50
 *  @描述：    TODO
 */
@Controller
public class  ItemController {

    @Reference
    private ItemService itemService;

    @Reference
    private ItemDescService itemDescService;


    @RequestMapping("/item/{id}.html")
    public String item(@PathVariable long id , Model model){

        //根据id得到商品的数据
        Item item = itemService.findItemById(id);

        //根据id得到商品的描述信息
        ItemDesc itemDesc = itemDescService.findDescById(id);

        //存储商品数据到model里
        model.addAttribute("item",item);
        model.addAttribute("itemDesc",itemDesc);

        return "item";
    }
}


