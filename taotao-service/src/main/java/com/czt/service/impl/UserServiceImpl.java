package com.czt.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.czt.mapper.UserMapper;
import com.czt.pojo.User;
import com.czt.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.service.impl
 *  @文件名:   UserServiceImpl
 *  @创建者:   Czt
 *  @创建时间:  2018/11/11 21:20
 *  @描述：    TODO
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
       private UserMapper userMapper;

    @Autowired
       private RedisTemplate<String,String> redisTemplate;

    @Override
    public Boolean check(String param, int type) {


        return null;


    }

    @Override
    public String selectUser(String ticket) {

        return null;

    }

    @Override
    public int addUser(User user) {


        user.setCreated(new Date());
        user.setUpdated(new Date());

        //MD5加密
        String password = user.getPassword();

        password = DigestUtils.md5DigestAsHex(password.getBytes());

        user.setPassword(password);


        int result = userMapper.insert(user);
        return  result;

    }

    @Override
    public String login(User user) {

        //MD5加密
        String password = user.getPassword();

        password = DigestUtils.md5DigestAsHex(password.getBytes());

        user.setPassword(password);
        List<User> list = userMapper.select(user);

        if (list.size()>0){

            //把key和用户信息存在redis中
            User user1 = list.get(0);
            String json = new Gson().toJson(user1);

            String key = "itt02_"+UUID.randomUUID().toString();

            redisTemplate.opsForValue().set(key,json);
            return  key;
        }
        return  null;
    }
}
