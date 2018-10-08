package com.czt.service;

import com.czt.pojo.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.service
 *  @文件名:   UserService
 *  @创建者:   Administrator
 *  @创建时间:  2018/9/10 14:21
 *  @描述：    TODO
 */
public interface UserService {

        void save();
        /**
         * 查询所有的用户
         * @return
         */
        List<User> selectAll();


        /**
         * 对用户执行分页处理
         * @param currentPage
         * @param pageSize
         * @return  pageInfo
         */
        PageInfo<User> findByPage(int currentPage , int pageSize);
}
