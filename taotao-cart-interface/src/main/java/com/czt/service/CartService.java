package com.czt.service;

import com.czt.pojo.Cart;

import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.service
 *  @文件名:   CartService
 *  @创建者:   Czt
 *  @创建时间:  2018/12/8 13:44
 *  @描述：    TODO
 */
public interface CartService {

    /**
     * 添加商品到购物车
     * @param userId 用户id
     * @param itemId 商品id
     * @param num    购买数量
     */
    void addToCart(long userId,long itemId,int num);


    /**
     * 根据用户id查询用户的购物车数据
     * @param userId 用户id
     * @return
     */

    List<Cart> queryCartByUserId(long userId);

    /**
     * 更新购物车数据
     * @param userId
     * @param itemId
     * @param num
     */
    void updateNumByCart(long userId,long itemId,int num);

    /**
     * 删除购物车数据
     * @param userId
     * @param itemId
     */
    void deleteItemByCart(Long userId,long itemId);



}
