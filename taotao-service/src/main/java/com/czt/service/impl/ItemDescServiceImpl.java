package com.czt.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.czt.mapper.ItemDescMapper;
import com.czt.pojo.ItemDesc;
import com.czt.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.service.impl
 *  @文件名:   ItemDescServiceImpl
 *  @创建者:   Czt
 *  @创建时间:  2018/11/26 18:26
 *  @描述：    TODO
 */
@Service
public class ItemDescServiceImpl implements ItemDescService {

    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public ItemDesc findDescById(long id) {
        return itemDescMapper.selectByPrimaryKey(id);
    }
}
