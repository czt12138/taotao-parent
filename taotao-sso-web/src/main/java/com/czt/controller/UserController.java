package com.czt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.czt.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public ResponseEntity<String> check(@PathVariable String param, @PathVariable int type,String callback){


        try {

            System.out.println("检测的用户名：" + param + ":" + type);

            Boolean b = userService.check(param, type);
            String result = "";

            if(!StringUtils.isEmpty(callback)){
                result =callback+"("+b+")";
            }
            else{
                 result = b +"";
            }
            return  ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @RequestMapping("/{ticket}")
    public ResponseEntity<String> selectUser(@PathVariable String ticket){

        try {
            String result = userService.selectUser(ticket);

            return  ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
