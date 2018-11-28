package com.czt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.czt.pojo.User;
import com.czt.service.ContentService;
import com.czt.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.controller
 *  @文件名:   IndexController
 *  @创建者:   Administrator
 *  @创建时间:  2018/9/27 15:44
 *  @描述：    TODO
 */
@Controller
public class IndexController {

    @Reference
    private ContentService contentService;

    @Reference
    private UserService userService;

    @RequestMapping("/page/{pageName}")
    public String page(@PathVariable String pageName){
        return  pageName;

    }



    @RequestMapping("/")
    public  String index(Model model, HttpServletRequest request){

        //获取ticket，到redis查询数据
        Cookie[] cookies = request.getCookies();
       if (cookies!=null){
           for ( Cookie cookie :cookies){

               String name = cookie.getName();
               System.out.println("name:" + name);
               if ("ticket".equals(name)){
                   String key = cookie.getValue();

                   User user = userService.findUser(key);

                   model.addAttribute("user",user);
                   break;
               }
           }

       }


        int categoryId=99;

        String json = contentService.selectByCategoryId(categoryId);

        model.addAttribute("list",json);

        return  "index";
    }

}

