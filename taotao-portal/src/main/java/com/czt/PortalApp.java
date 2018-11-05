package com.czt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt
 *  @文件名:   PortalApp
 *  @创建者:   Administrator
 *  @创建时间:  2018/9/27 15:34
 *  @描述：    TODO
 */
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
public class PortalApp {
     public static void main(String [] args){
         SpringApplication.run(PortalApp.class,args);
     }
}
