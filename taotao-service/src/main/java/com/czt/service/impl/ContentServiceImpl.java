package com.czt.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.czt.mapper.ContentMapper;
import com.czt.pojo.Content;
import com.czt.service.ContentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

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

        content.setCreated(new Date());
        content.setUpdated(new Date());

        int result  = contentMapper.insert(content);


        return result;
    }

    @Override
    public PageInfo<Content> list(long categoryId, int page, int rows) {

        //1. 分页的设置
        PageHelper.startPage(page , rows);


        //
        Content content = new Content();
        content.setCategoryId(categoryId);
        List<Content> list = contentMapper.select(content);


        return new PageInfo<>(list);
    }

    @Override
    public int edit(Content content) {

        content.setUpdated(new Date());

        int result = contentMapper.updateByPrimaryKeySelective(content);

        return result;
    }

    @Override
    public int delete(String ids) {////ids: 97,98  | ids:97

       /* Content c = new Content();
        c.setId(id);
    */
        int result = 0 ;
        for (String id : ids.split(",")) {
            result += contentMapper.deleteByPrimaryKey(Long.parseLong(id));
        }

        return result ;
    }

}
