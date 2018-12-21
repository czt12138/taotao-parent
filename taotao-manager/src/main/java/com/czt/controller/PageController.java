package com.czt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt
 *  @文件名:   PageController
 *  @创建者:   czt
 *  @创建时间:  2018/9/18 11:45
 *  @描述：    淘淘商城后台首页
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String index(){

        System.out.println("欢迎来到淘淘商城后台首页");

        return "index";
    }



    @RequestMapping("/rest/page/{pageName}")
    public String page(@PathVariable String pageName){

        System.out.println("pageName=" + pageName);
        return pageName;
    }


}
