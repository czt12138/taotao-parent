package com.czt.controller;

import com.czt.pojo.User;
import com.czt.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.controller
 *  @文件名:   UserController
 *  @创建者:   Administrator
 *  @创建时间:  2018/9/10 14:12
 *  @描述：    TODO
 */
@RestController
public class UserController {


     // @Reference 注意： 这里使用的是dubbo的注解
      private UserService userService;

    @RequestMapping("save")
        public String save(){

            System.out.println("调用了save方法！！");

            return  "success!!";
        }

    @RequestMapping("selectAll")
    public String  selectAll(){
        System.out.println("调用了selectAll方法~！·");


        List<User> list = userService.selectAll();

        for(User user: list){
            System.out.println("user=" + user);
        }
        return "scuccess~!~!";
    }



    @RequestMapping("findByPage")
    public PageInfo<User> findByPage(int currentPage, int pageSize){
        System.out.println("调用了UserController的findByPage方法~！·");


        PageInfo<User> page = userService.findByPage(currentPage, pageSize);

        //直接返回对象，在页面上显示的是json字符串
        return page;

    }
}
