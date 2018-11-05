package com.czt.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.czt.mapper.ContentMapper;
import com.czt.pojo.Content;
import com.czt.service.ContentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;

import java.util.*;

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

    @Autowired
    private RedisTemplate<String ,String> redisTemplate;

    @Override
    public int add(Content content) {

        content.setCreated(new Date());
        content.setUpdated(new Date());

        int result  = contentMapper.insert(content);

        //删除redis数据
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("bigAD","");

        return  result;
    }

    @Override
    public PageInfo<Content> list(long categoryId, int page, int rows) {


        PageHelper.startPage(page , rows);

        Content content = new Content();
        content.setCategoryId(categoryId);
        List<Content> list = contentMapper.select(content);

        return new PageInfo<>(list);
    }

    @Override
    public int edit(Content content) {

        content.setUpdated(new Date());

        int result = contentMapper.updateByPrimaryKeySelective(content);



        //删除redis数据
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("bigAD","");
        return result;
    }

    @Override
    public int delete(String ids) {


        int result = 0 ;
        for (String id : ids.split(",")) {
            result += contentMapper.deleteByPrimaryKey(Long.parseLong(id));
        }
        //删除redis数据
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("bigAD","");


        return result ;
    }

    //使用到redis缓存
    @Override
    public String selectByCategoryId(long cid) {


        ValueOperations<String, String> operations = redisTemplate.opsForValue();

        String json = operations.get("bigAD");

        System.out.println("缓存里的广告数据：" + json);

        if (!StringUtils.isEmpty(json)){

            System.out.println("缓存里有数据，直接返回");
            return  json;
        }

        System.out.println("缓存里没有数据，执行查询数据库的操作");
         Content c = new Content();

         c.setCategoryId(cid);

        List<Content> contents = contentMapper.select(c);

        List<Map<String,Object>> list = new ArrayList<>();

        for (Content content : contents) {

            Map<String,Object> map =  new HashMap<>();
            map.put("src",content.getPic());
            map.put("width",670);
            map.put("height",240);
            map.put("href",content.getUrl());
            list.add(map);
        }

         json = new Gson().toJson(list);
         operations.set("bigAD",json);

        System.out.println("从数据库查询到的数据要存进redis里");
        return json;
    }

}
