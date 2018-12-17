package com.czt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.czt.cart.CartCookieService;
import com.czt.pojo.Cart;
import com.czt.pojo.User;
import com.czt.service.CartService;
import com.czt.util.CookieUtil;
import com.czt.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.controller
 *  @文件名:   CartController
 *  @创建者:   Czt
 *  @创建时间:  2018/12/8 13:40
 *  @描述：    处理购物车
 */
@Controller
public class CartController {

    @Reference
     private CartService cartService;

    @Autowired
    private CartCookieService cartCookieService;

    @Autowired
    private RedisTemplate<String,String> template;

    //加入购物车
    @RequestMapping("/cart/add/{id}.html")
    public  String addToCart(@PathVariable  long id, int num, HttpServletRequest request, HttpServletResponse response){

        //从cookie取出redis中的key,通过key查询用户数据，获取用户id
        String ticket = CookieUtil.findTicket(request);
        //有用户已经登录
        if (ticket != null){

            //从reid获取用户信息
            User user = RedisUtil.findUserByTicket(template,ticket);
            cartService.addToCart(user.getId(),id,num);

        }else{
            //没有用户登录
            cartCookieService.addToCart(id,num,request,response);

        }
        return  "cartSuccess";
      }

      //显示购物车信息
     @RequestMapping("/cart/cart.html")
     public String show(HttpServletRequest request,Model model){

         //从cookie取出redis中的key
         String ticket = CookieUtil.findTicket(request);

         List<Cart> cartlist = null;
         //有用户已经登录
         if (ticket != null) {

             //从reid获取用户信息
             User user = RedisUtil.findUserByTicket(template,ticket);
             //根据用户id从reidis中获取用户的购物车信息
             cartlist = cartService.queryCartByUserId(user.getId());

         }else{
             //没有用户登录
             cartlist = cartCookieService.findCartByCookie(request);
         }
         model.addAttribute("cartList",cartlist);
         return "cart";
      }

      //由于有注解@controller,不管有无返回值，都会默认要跳转
     //有返回值就跳转具体页面，否则就跳转默认的根路径 “/”
      @RequestMapping("/service/cart/update/num/{id}/{num}")
      @ResponseBody
      public  void updateNumByCart(@PathVariable  long id,@PathVariable int num,HttpServletRequest request,HttpServletResponse response){

          //从cookie取出redis中的key
          String ticket = CookieUtil.findTicket(request);
          //有用户已经登录
          if (ticket != null) {

              //从reid获取用户信息
              User user = RedisUtil.findUserByTicket(template,ticket);
              //修改购物车数据
              cartService.updateNumByCart(user.getId(), id, num);
         }else {
              //没有用户登录
              cartCookieService.updateCartByCookie(id,num,request,response);
         }

      }

      @RequestMapping("/cart/delete/{id}.shtml")
      public  String deleteItemByCart(@PathVariable long id,HttpServletRequest request,HttpServletResponse response){

          //从cookie取出redis中的key
          String ticket = CookieUtil.findTicket(request);
          //有用户已经登录
          if (ticket != null) {

              //从reid获取用户信息
              User user = RedisUtil.findUserByTicket(template,ticket);
              //修改购物车数据
              cartService.deleteItemByCart(user.getId(),id);
          }else {
              //没有用户登录
              cartCookieService.deleteCartByCookie(id,request,response);
          }

          //重定向到购物车页面
          return  "redirect:/cart/cart.html";

      }
}
