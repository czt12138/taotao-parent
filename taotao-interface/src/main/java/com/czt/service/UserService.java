package com.czt.service;

import com.czt.pojo.User;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.service
 *  @文件名:   UserService
 *  @创建者:   Administrator
 *  @创建时间:  2018/9/10 14:21
 *  @描述：    TODO
 */
public interface UserService {

     Boolean   check(String param,int type);

     String selectUser(String ticket);

     //注册用户
     int addUser(User user);

     String login(User user);
}
