package com.czt.cart.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.czt.cart.CartCookieService;
import com.czt.pojo.Cart;
import com.czt.pojo.Item;
import com.czt.service.ItemService;
import com.czt.util.CookieUtil;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.cart.impl
 *  @文件名:   CartCookieServiceImpl
 *  @创建者:   Czt
 *  @创建时间:  2018/12/10 9:39
 *  @描述：    TODO
 */
@Service
public class CartCookieServiceImpl implements CartCookieService {

    @Reference
    private ItemService itemService;

    @Override
    public void addToCart(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {

        //从cookie获取以前的购物车数据
        List<Cart> cartlist = CookieUtil.findCartByCookie(request);
        //cookie没有购物车数据
        if (cartlist == null) {
            cartlist = new ArrayList<>();
        }
        //cookie有数据
        Cart c = null;
        for (Cart cart : cartlist) {
            if (cart.getItemId() == itemId){
                c = cart;
                break;
            }
        }
        if (c != null){
            //购物车有这件商品
            c.setNum(c.getNum()+num);
            c.setUpdate(new Date());
        }else {
            //购物车没这件商品
            Item item = itemService.findItemById(itemId);
            Cart cart = new Cart();
            cart.setItemId(itemId);
            cart.setItemTitle(item.getTitle());
            cart.setItemPrice(item.getPrice());
            cart.setItemImage(item.getImages()[0]);
            cart.setNum(num);
            cart.setCreate(new Date());
            cart.setUpdate(new Date());
            cartlist.add(cart);
        }

        //把新的购物车数据存到cookie中
        String json = new Gson().toJson(cartlist);

        try {
            System.out.println("现在购物车里的内容：" + json);
            //将json字符串用url编码
            json = URLEncoder.encode(json,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("现在购物车里的内容222：" + json);
        Cookie cookie = new Cookie("iit_cart",json);
        cookie.setMaxAge(60 * 60 * 24 * 7);

        //默认地址，cookie的路径是当前请求的路径（cart/add），但显示购物车数据的地址是cart/cart
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    @Override
    public List<Cart> findCartByCookie(HttpServletRequest request) {

        return CookieUtil.findCartByCookie(request);

    }

    @Override
    public void updateCartByCookie(long itemId,int num,HttpServletRequest request,HttpServletResponse response) {

        List<Cart> cartlist = findCartByCookie(request);

        for (Cart cart : cartlist) {
            if(itemId == cart.getItemId()){

                cart.setNum(num);
                cart.setUpdate(new Date());
                break;
            }
        }

        try {
            String json = new Gson().toJson(cartlist);
            //将json字符串用url编码
            json = URLEncoder.encode(json,"UTF-8");
            Cookie cookie = new Cookie("iit_cart",json);
            cookie.setMaxAge(60 * 60 * 24 * 7);

            //默认地址，cookie的路径是当前请求的路径（cart/add），但显示购物车数据的地址是cart/cart
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCartByCookie(long itemId,HttpServletRequest request,HttpServletResponse response) {

        List<Cart> cartlist = findCartByCookie(request);

        for (Cart cart : cartlist) {
            if(itemId == cart.getItemId()){

                cartlist.remove(cart);
                break;
            }
        }

        try {
            String json = new Gson().toJson(cartlist);
            //将json字符串用url编码
            json = URLEncoder.encode(json,"UTF-8");
            Cookie cookie = new Cookie("iit_cart",json);
            cookie.setMaxAge(60 * 60 * 24 * 7);

            //默认地址，cookie的路径是当前请求的路径（cart/add），但显示购物车数据的地址是cart/cart
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
