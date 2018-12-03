package com.czt.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.czt.mapper.ItemDescMapper;
import com.czt.mapper.ItemMapper;
import com.czt.pojo.Item;
import com.czt.pojo.ItemDesc;
import com.czt.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;

import java.util.Date;
import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.service.impl
 *  @文件名:   ItemServiceImpl
 *  @创建者:   Administrator
 *  @创建时间:  2018/9/26 10:08
 *  @描述：    TODO
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;

    @Autowired
    private JmsMessagingTemplate template;

    @Override
    public int addItem(Item item, String desc) {


        //从页面传递过来的Item还不完整
        long id = (long) (System.currentTimeMillis() + Math.random() * 10000);

        item.setId(id);
        item.setStatus(1);
        item.setCreated(new Date());
        item.setUpdated(new Date());


         int result = itemMapper.insertSelective(item);

        //添加desc表
        ItemDesc itemDesc = new ItemDesc();

        itemDesc.setItemId(id);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());

        itemDescMapper.insertSelective(itemDesc);


        //添加完商品，需要发送出来消息，然后让搜索系统去更新索引库。
        template.convertAndSend("item-save" , id);

        return  result;
    }

    @Override
    public PageInfo<Item> list(int page, int rows) {

        //必须设置这一行，才能实现分页效果
        PageHelper.startPage(page,rows);

        List<Item> list = itemMapper.select(null);

        return new PageInfo<Item>(list);
    }

    @Override
    public int show_desc(long item_id) {

        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item_id);

       int result = itemDescMapper.updateByPrimaryKeySelective(itemDesc);

        System.out.println("result=" + result);

        return result;

    }

    @Override
    public int show_cat(long id) {

        Item item = new Item();
        item.setId(id);
        int result = itemMapper.updateByPrimaryKeySelective(item);
        System.out.println("result=" + result);

        return result;
    }


    @Override
    public int updateItem(Item item){

         int result = itemMapper.updateByPrimaryKeySelective(item);

        System.out.println("result:"+result);

        return result;
    }

    @Override
    public int deleteItem(String  ids) {


        int result = 0;
        for (String s : ids.split(",")){

             result += itemMapper.deleteByPrimaryKey(Long.parseLong(s));

        }
        return  result;

    }

   @Override
    public Item findItemById(long id) {

        return itemMapper.selectByPrimaryKey(id);
    }





}
