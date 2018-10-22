package com.czt.service;

import com.czt.pojo.Item;
import com.github.pagehelper.PageInfo;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.service
 *  @文件名:   ItemService
 *  @创建者:   Administrator
 *  @创建时间:  2018/9/26 10:07
 *  @描述：    TODO
 */
public interface ItemService {

    int addItem(Item item,String desc);

    PageInfo<Item> list(int page, int rows);

    int show_desc(long item_id);

    int show_cat(long id);

    int updateItem(Item item);

    int deleteItem(String  ids);


}
