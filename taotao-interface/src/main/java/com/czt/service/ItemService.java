package com.czt.service;

import com.czt.pojo.Item;
import com.czt.pojo.ItemDesc;
import com.github.pagehelper.PageInfo;

import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.service
 *  @文件名:   ItemService
 *  @创建者:   czt
 *  @创建时间:  2018/9/26 10:07
 *  @描述：    商品处理接口类
 */
public interface ItemService {

    int addItem(Item item,String desc);

    PageInfo<Item> list(int page, int rows);

    List<ItemDesc> show_desc(long item_id);



    int updateItem(Item item);

    int deleteItem(String  ids);

    Item findItemById(long id);


}
