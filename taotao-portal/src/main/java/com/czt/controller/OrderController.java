package com.czt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.czt.pojo.Cart;
import com.czt.pojo.Order;
import com.czt.pojo.User;
import com.czt.service.CartService;
import com.czt.service.OrderService;
import com.czt.util.CookieUtil;
import com.czt.util.RedisUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Reference
    private OrderService orderService;

    @RequestMapping("/order/order-cart.shtml")
    public  String create(HttpServletRequest request, Model model){

        //判断用户是否登录,没有登录就得登录，登了就会显示订单详情
        User user = (User) request.getAttribute("user");
        List<Cart> carts = cartService.queryCartByUserId(user.getId());

        model.addAttribute("carts",carts);


        return "order-cart";
    }

    @RequestMapping("/service/order/submit")
    @ResponseBody
    public Map<String, String> addOrder(Order order,  HttpServletRequest request){

        String ticket = CookieUtil.findTicket(request);

        User user = RedisUtil.findUserByTicket(template, ticket);
        Long userId = user.getId();

        String orderId = userId + RedisUtil.getOrderId(template,"order_"+userId);

        order.setUserId(userId);
        order.setBuyerNick(user.getUsername());

        orderService.addOrder(order,orderId);

        Map<String,String> map = new HashMap<>();
        map.put("status","200");
        map.put("data",orderId);

        return  map;
    }

    @RequestMapping("/order/success.html")
    public  String  orderSuccess(String id,Model model){


        Order order = orderService.findOrderById(id);

        model.addAttribute("order",order);

        // 设置送达时间
        String date = new DateTime().plusDays(2).toString("yyyy年MM月dd日HH时mm分ss秒SSS毫秒");
        model.addAttribute("date",date);

        return "success";
    }

}
