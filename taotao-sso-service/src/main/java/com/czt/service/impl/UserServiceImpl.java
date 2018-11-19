package com.czt.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.czt.mapper.UserMapper;
import com.czt.pojo.User;
import com.czt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.List;

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

        User user = new User();

        switch (type){
            case  1:
                user.setUsername(param);
                break;
            case 2:
                user.setPhone(param);
                break;
            case 3:
                user.setEmail(param);
                break;
            default:
                user.setUsername(param);
                break;

        }

        List<User> list = userMapper.select(user);

        return list.size()>0?false:true;


    }

    @Override
    public String selectUser(String ticket) {

        String key = "iit_"+ticket;


        //从redis获取用户信息
        return redisTemplate.opsForValue().get(key);

    }

    @Override
    public int addUser(User user) {


        user.setCreated(new Date());
        user.setUpdated(new Date());

        int result = userMapper.insert(user);
        return  result;

    }
}
