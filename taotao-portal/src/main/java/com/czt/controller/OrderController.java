package com.czt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.czt.pojo.Cart;
import com.czt.pojo.User;
import com.czt.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.controller
 *  @文件名:   OrderController
 *  @创建者:   Czt
 *  @创建时间:  2018/12/13 21:33
 *  @描述：   处理订单
 */
@Controller
public class OrderController {

    @Reference
    private CartService cartService;

    @Autowired
    private RedisTemplate<String,String> template;

    @RequestMapping("/order/order-cart.shtml")
    public  String create(HttpServletRequest request, Model model){

        //判断用户是否登录,没有登录就得登录，登了就会显示订单详情
        User user = (User) request.getAttribute("user");
        List<Cart> carts = cartService.queryCartByUserId(user.getId());

        model.addAttribute("carts",carts);


        return "order-cart";
    }

}
