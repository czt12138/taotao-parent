package com.czt.service.impl;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.serviceimpl
 *  @文件名:   UserServiceImpl
 *  @创建者:   Administrator
 *  @创建时间:  2018/9/10 14:24
 *  @描述：    TODO
 */


import com.alibaba.dubbo.config.annotation.Service;
import com.czt.mapper.UserMapper;
import com.czt.pojo.User;
import com.czt.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//注意，这个注解使用的是dubbo的注解，而不是Spring提供的@Service
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public void save() {
        System.out.println("调用了save方法~！");
    }

    @Override
    public List<User> selectAll() {


        return userMapper.selectAll();
    }

    @Override
    public PageInfo<User> findByPage(int currentPage, int pageSize) {


        PageHelper.startPage(currentPage , pageSize);

        List<User> list = userMapper.select(null);

        return new PageInfo<>(list);
    }
}
