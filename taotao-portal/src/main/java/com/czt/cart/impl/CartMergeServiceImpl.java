package com.czt.cart.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.czt.cart.CartCookieService;
import com.czt.cart.CartMergeService;
import com.czt.pojo.Cart;
import com.czt.pojo.User;
import com.czt.service.CartService;
import com.czt.util.RedisUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.cart
 *  @文件名:   CartMergeServiceImpl
 *  @创建者:   Czt
 *  @创建时间:  2018/12/13 20:49
 *  @描述：    TODO
 */
@Service
public class CartMergeServiceImpl implements CartMergeService {

    @Reference
    private CartService cartService;

    @Autowired
    private CartCookieService cartCookieService;

    @Autowired
    private RedisTemplate<String,String> template;

    @Override
    public void mergeCart(String ticket, HttpServletRequest request, HttpServletResponse response){

        //查询redis的购物车信息
        User user = RedisUtil.findUserByTicket(template, ticket);
        long userId = user.getId();
        List<Cart> redislist = cartService.queryCartByUserId(userId);

        //查询cookie的购物车信息
        List<Cart> cookielist = cartCookieService.findCartByCookie(request);
        for (Cart cart : cookielist) {

            //判断redis是否有这件商品
            if(redislist.contains(cart)){
                int index = redislist.indexOf(cart);
                Cart c = redislist.get(index);
                c.setNum(cart.getNum()+c.getNum());
                c.setUpdate(new Date());
            }else{

                redislist.add(cart);
            }
        }

        //合并数据到redis中
        String json = new Gson().toJson(redislist);
        template.opsForValue().set("iit_"+userId,json);

        Cookie cookie = new Cookie("iit_cart","");
        cookie.setMaxAge(0);
        //默认地址，cookie的路径是当前请求的路径（cart/add），但显示购物车数据的地址是cart/cart
        //消除cookie
        cookie.setPath("/");
        response.addCookie(cookie);


    }
}
