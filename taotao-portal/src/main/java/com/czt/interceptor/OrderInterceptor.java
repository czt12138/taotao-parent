package com.czt.interceptor;

import com.czt.pojo.User;
import com.czt.util.CookieUtil;
import com.czt.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.interceptor
 *  @文件名:   OrderInterceptor
 *  @创建者:   Czt
 *  @创建时间:  2018/12/13 21:52
 *  @描述：    TODO
 */
@Component
public class OrderInterceptor implements HandlerInterceptor {

    @Autowired
     private RedisTemplate<String,String> template;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String ticket = CookieUtil.findTicket(request);

        //cookie中的ticket为空就表示没有用户登录
        if(StringUtils.isEmpty(ticket)){

            System.out.println("没有登录，所以被拦截了");

            //获取当前请求地址(已经登录时要跳转的页面)
            String uri = request.getRequestURI();
            System.out.println("uri:" + uri);

            String url = request.getRequestURI().toString();
            System.out.println("url:" + url);

            //跳转到登录界面
            response.sendRedirect("/page/login.shtml?redirect="+uri);
            return  false;
        }
        //判断redis是否真的有用户信息
        User user = RedisUtil.findUserByTicket(template, ticket);
        if (user == null){

            //跳转到登录界面
            response.sendRedirect("/page/login.shtml");
            return  false;

        }
        //把user的信息存在session中，以便使用
        request.setAttribute("user",user);

        System.out.println("有登录，可以查看订单详情啦");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
