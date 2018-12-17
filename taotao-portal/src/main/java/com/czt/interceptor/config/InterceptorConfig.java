package com.czt.interceptor.config;

import com.czt.interceptor.OrderInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.interceptor.config
 *  @文件名:   InterceptorConfig
 *  @创建者:   Czt
 *  @创建时间:  2018/12/13 21:54
 *  @描述：    TODO
 */
@Component
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private OrderInterceptor orderInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(orderInterceptor).addPathPatterns("/order/**");
    }
}
