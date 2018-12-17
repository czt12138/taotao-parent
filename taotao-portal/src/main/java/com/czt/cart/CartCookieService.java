package com.czt.cart;

import com.czt.pojo.Cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.cart
 *  @文件名:   CartCookieService
 *  @创建者:   Czt
 *  @创建时间:  2018/12/10 9:39
 *  @描述：    TODO
 */
public interface CartCookieService {



    /**
     * （未登录时）添加商品到购物车，商品添加到cookie中
     * @param itemId
     * @param num
     * @param request
     * @param response
     */
    void addToCart(long itemId, int num, HttpServletRequest request, HttpServletResponse response);

    /**
     * 从cooik中查询购物车数据
     * @param request
     * @return
     */
    List<Cart> findCartByCookie(HttpServletRequest request);

    /**
     * 修改购物车
     * @param itemId
     * @param num
     * @param request
     * @param response
     */
    void updateCartByCookie(long itemId,int num,HttpServletRequest request,HttpServletResponse response);

    /**
     * 删除购物车
     * @param itemId
     */
    void deleteCartByCookie(long itemId,HttpServletRequest request,HttpServletResponse response);
}
