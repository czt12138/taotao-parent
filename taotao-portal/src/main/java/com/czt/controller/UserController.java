package com.czt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.czt.cart.CartMergeService;
import com.czt.pojo.User;
import com.czt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.controller
 *  @文件名:   UserController
 *  @创建者:   Czt
 *  @创建时间:  2018/11/12 14:59
 *  @描述：    用户登录注册，合并购物车
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @Autowired
    private CartMergeService cartMergeService;

    @RequestMapping("/doRegister.shtml")
    @ResponseBody
    public Map<String, String> register(User user){


        System.out.println("user:" + user);

        int result = userService.addUser(user);

        Map<String,String> map = new HashMap<>();
        if (result>0){
            map.put("status","200");

        }
        else {
            map.put("status","404");
        }

        return  map;
    }

    @PostMapping("/doLogin.shtml")
    @ResponseBody
    public Map<String,String> login(User user, HttpServletResponse response, HttpServletRequest request){

        Map<String,String>  map = new HashMap<>();

        //ticket:redis中用户信息的key
        String ticket = userService.login(user);
        if(!StringUtils.isEmpty(ticket)){

            //把用户登陆成功后的凭证存到cookie中
            Cookie cookie = new Cookie("ticket",ticket);
            cookie.setMaxAge(60 * 60 * 24 * 7);//有效期
            cookie.setPath("/"); //设置路径
            response.addCookie(cookie);
            map.put("status","200");

            //登录成功跳转页面
            map.put("success","http://www.taotao.com");

            //合并购物车信息
            cartMergeService.mergeCart(ticket,request,response);

        }

        return  map;
    }

}
