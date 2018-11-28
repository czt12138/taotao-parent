package com.czt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.czt.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.controller
 *  @文件名:   UserController
 *  @创建者:   Czt
 *  @创建时间:  2018/11/11 21:12
 *  @描述：    TODO
 */
@Controller
@RequestMapping("/user")
public class UserController {



        @Reference
        private UserService userService;

        @RequestMapping("/check/{param}/{type}")
        @ResponseBody
        public String check(@PathVariable  String param ,@PathVariable int type , String callback){

            Boolean flag = userService.check(param, type);

            System.out.println(flag ? "可以使用" : "不能使用");

            String result = callback+"("+flag+")";

            return result;
        }


        @RequestMapping("/user/{ticket}")
        @ResponseBody
        public String ticket(@PathVariable String ticket){

            System.out.println("根据ticket获取用户信息");

            String json = userService.selectUser(ticket);
            System.out.println("json=" + json);

            return json;
        }

    }

