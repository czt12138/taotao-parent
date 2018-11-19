package com.czt.test;

import com.czt.pojo.User;
import com.google.gson.Gson;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Date;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.test
 *  @文件名:   SsoTest
 *  @创建者:   Czt
 *  @创建时间:  2018/11/12 10:34
 *  @描述：    TODO
 */
public class SsoTest {

      @Test
      public  void testTicket(){

            User user = new User();
            user.setId(22L);
            user.setUsername("lip");
            user.setEmail("abc@qq.com");
            user.setPhone("2342423423423");
            user.setPassword("111111");
            user.setCreated(new Date());
            user.setUpdated(new Date());

            String json = new Gson().toJson(user);

            Jedis jedis = new Jedis("192.168.227.128",7072);

            jedis.set("iit_abc123",json);
            jedis.close();
      }

}
