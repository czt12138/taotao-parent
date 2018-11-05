package com.czt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt
 *  @文件名:   ServiceApp
 *  @创建者:   Administrator
 *  @创建时间:  2018/9/10 14:16
 *  @描述：    TODO
 */

//设置自动检测工作，不包含数据源的检测

//@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
public class ServiceApp {

    public static void main(String [] args){
        SpringApplication.run(ServiceApp.class , args);
    }
}