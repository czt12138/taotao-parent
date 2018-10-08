package com.czt.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.czt.mapper.ContentMapper;
import com.czt.pojo.Content;
import com.czt.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.service.impl
 *  @文件名:   ContentServiceImpl
 *  @创建者:   Administrator
 *  @创建时间:  2018/9/29 14:51
 *  @描述：    TODO
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;


    @Override
    public int add(Content content) {

     return    contentMapper.insert(content);

    }

}
