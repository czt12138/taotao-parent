package com.czt.util;

import com.czt.pojo.User;
import com.google.gson.Gson;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.util
 *  @文件名:   RedisUtil
 *  @创建者:   Czt
 *  @创建时间:  2018/12/8 20:40
 *  @描述：    redis
 */
public class RedisUtil {



   public  static  User findUserByTicket(RedisTemplate<String,String> template,String ticket){

       //从reid获取用户信息
       String json = template.opsForValue().get(ticket);
       User user = null;
       if (!StringUtils.isEmpty(json)){
           user = new Gson().fromJson(json, User.class);
       }

       return  user;

   }

   public static String getOrderId(RedisTemplate<String,String> template,String key){


       return  template.opsForValue().increment(key,1)+"";
   }
}
