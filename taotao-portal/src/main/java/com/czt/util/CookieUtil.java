package com.czt.util;

import com.czt.pojo.Cart;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.util
 *  @文件名:   CookieUtil
 *  @创建者:   Czt
 *  @创建时间:  2018/12/8 13:59
 *  @描述：    TODO
 */
public class CookieUtil {

    public static String findTicket(HttpServletRequest request){

        //从cookie取出redis中的key,通过key查询用户数据，获取用户id
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies) {

                String name = cookie.getName();
                if ("ticket".equals(name)){
                     return cookie.getValue();
                }
            }
        }
        return  null;
    }

    /**
     * 从cookie获取以前的购物车数据
     * @param request
     * @return
     */
    public  static List<Cart> findCartByCookie(HttpServletRequest request){

        List<Cart> list =null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                String name = cookie.getName();

                System.out.println("从cookie中获取购物车数据,name:" + name);
                if("iit_cart".equals(name)){
                    String json = cookie.getValue();

                    //url解码
                    try {
                        json  = URLDecoder.decode(json,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    list = new Gson().fromJson(json, new TypeToken<List<Cart>>() {}.getType());
                    break;
                }
            }
        }
        return  list;
    }
}
