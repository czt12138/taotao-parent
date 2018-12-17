package com.czt.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.czt.pojo.Cart;
import com.czt.pojo.Item;
import com.czt.service.CartService;
import com.czt.service.ItemService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.service.impl
 *  @文件名:   CartServiceImpl
 *  @创建者:   Czt
 *  @创建时间:  2018/12/8 13:49
 *  @描述：    TODO
 */
@Service
public class CartServiceImpl  implements CartService{

    @Reference
    private ItemService itemService;

    @Autowired
    private RedisTemplate<String,String> template;

    /**
     * 已有用户登录时添加购物车数据
     * @param userId 用户id
     * @param itemId 商品id
     * @param num    购买数量
     */
    @Override
    public void addToCart(long userId, long itemId, int num) {

        //根据itemId查询要添加的商品数据
        Item item = itemService.findItemById(itemId);

        //根据userId查询redis中的购物车数据，判断数据是否重复
        //重复就修改数据，否则就是全新的数据
        List<Cart> cartlist = queryCartByUserId(userId);

        Cart c = null;
        //遍历购物车
        for (Cart cart : cartlist) {

            //判断要添加的商品和购物车的商品是否一样
            if (itemId == cart.getItemId()){
                c = cart;
                break;
            }
        }

        if (c != null){
            //有重复的商品
             c.setNum(c.getNum()+num);
        }else {
            //没有重复的商品
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

        String json = new Gson().toJson(cartlist);
        //把购物车数据存到redis中
        template.opsForValue().set("iit_"+userId,json);


    }



    @Override
    public List<Cart> queryCartByUserId(long userId) {

        //根据用户id从redis获取到用户的购物车数据
        String json = template.opsForValue().get("iit_"+userId);

        //把json字符串转化为list集合
        List<Cart> list = new Gson().fromJson(json, new TypeToken<List<Cart>>() {
        }.getType());

        //第一次加入购物车时，reids是没有购物车信息的
        if (list == null){
            list = new ArrayList<>();
        }

        return list;
    }

    @Override
    public void updateNumByCart(long userId, long itemId, int num) {

        //根据用户id从redis获取到用户的购物车数据
        String json = template.opsForValue().get("iit_"+userId);

        //把json字符串转化为list集合
        List<Cart> list = new Gson().fromJson(json, new TypeToken<List<Cart>>() {
        }.getType());

        //遍历购物车，修改数据
        for (Cart cart : list) {

            if(itemId == cart.getItemId()){
                cart.setNum(num);
                cart.setUpdate(new Date());
                break;
            }
        }

        json = new Gson().toJson(list);
         //把修改数据存进redis中
        template.opsForValue().set("iit_"+userId,json);

    }

    @Override
    public void deleteItemByCart(Long userId, long itemId) {

        String json = template.opsForValue().get("iit_" + userId);
        //把json字符串转化为list集合
        List<Cart> list = new Gson().fromJson(json, new TypeToken<List<Cart>>() {
        }.getType());
        //删除购物车数据
        for (Cart cart : list) {

            if(itemId == cart.getItemId()){

                list.remove(cart);
                break;
            }
        }

        json = new Gson().toJson(list);
        template.opsForValue().set("iit_" + userId,json);


    }
}
