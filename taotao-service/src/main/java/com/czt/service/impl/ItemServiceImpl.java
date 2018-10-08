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

    @Override
    public int addItem(Item item, String desc) {

        //添加Item表
        //itemMapper.insert(); //添加数据
        //itemMapper.insertSelective(); //添加数据  Selective：有选择性

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
        item.setCreated(new Date());
        item.setUpdated(new Date());

        itemDescMapper.insertSelective(itemDesc);

        return  result;
    }

    @Override
    public PageInfo<Item> list(int page, int rows) {

        //必须设置这一行，才能实现分页效果
        PageHelper.startPage(page,rows);

        List<Item> list = itemMapper.select(null);

        return new PageInfo<Item>(list);
    }
}
