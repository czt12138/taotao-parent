package com.czt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.czt.pojo.Item;
import com.czt.service.ItemService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.controller
 *  @文件名:   ItemController
 *  @创建者:   czt
 *  @创建时间:  2018/9/26 10:01
 *  @描述：    商品处理控制器
 */

@Controller //视图解析器可以解析视图，从而跳转页面
public class ItemController {

    @Reference
    private ItemService itemService;

    /**
     * 添加商品
     * @param item
     * @param desc
     * @return
     */
    //添加商品Item对象要添加到item表里
    //商品的描述，使用desc来接受，然后要添加到item_desc表里
         @RequestMapping(value = "/rest/item", method = RequestMethod.POST)
         @ResponseBody
         public  String addItem(Item item,String desc){

            int result = itemService.addItem(item,desc);

             System.out.println("result=" + result);

             return  "success";

         }


    /**
     * 查询所有商品
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/rest/item", method = RequestMethod.GET)
    @ResponseBody
         public  Map<String, Object> list(int page,int rows) {

        PageInfo<Item> pageInfo = itemService.list(page, rows);

        //easyui的 数据表格显示数据，要求有固定格式，json里面有total属性和rows属性
        //total属性表示总记录
        //rows属性当前页的集合数据，也就是List集合

        Map<String ,Object> map = new HashMap<>();

        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());

        return map;
    }

    /**
     * 显示描述信息
     * @param item_id
     * @return
     */
    @RequestMapping(value = "/rest/item/desc/item_id",method = RequestMethod.POST)
    @ResponseBody
    public String show_desc(long item_id){

        int result = itemService.show_desc(item_id);
        System.out.println("result=" + result);

        return  "success!!";

    }

    @RequestMapping(value = "/rest/item/cat/id",method = RequestMethod.POST)
    @ResponseBody
    public String show_cat(Long id){

        int result = itemService.show_cat(id);
        System.out.println("result=" + result);

        return  "success!!";

    }


    @RequestMapping(value = "/rest/item/update",method = RequestMethod.POST)
    @ResponseBody
    public String updateItem(Item item){

           int result = itemService.updateItem(item);
           System.out.println("result=" + result);

        return  "success!!";

    }

    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public Map<String,Object> deleteItem(String  ids){

         Map<String,Object> map = new HashMap<>();

        int result = itemService.deleteItem(ids);
         if(result>0){
             map.put("status",200);
         }
         else{
             map.put("status",500);
         }

         return map;
    }




}
