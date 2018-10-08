package com.czt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/")
    public  String index(){

        return  "index";
    }

}

