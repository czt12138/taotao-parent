package com.czt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt
 *  @文件名:   SsoWebApp
 *  @创建者:   Administrator
 *  @创建时间:  2018/11/5 10:01
 *  @描述：    TODO
 */

    //设置自动检测工作，不包含数据源的检测
    @EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
    @SpringBootApplication
    public class SsoWebApp {
        public static void main(String [] args) {

            SpringApplication.run(SsoWebApp.class,args);
        }
    }