package com.czt.listener;

import com.czt.quartz.OrderScheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoaderListener;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.listener
 *  @文件名:   OrderQuartzListener
 *  @创建者:   Czt
 *  @创建时间:  2018/12/20 14:09
 *  @描述：    TODO
 */
@Component
public class OrderQuartzListener extends ContextLoaderListener{

    @Resource(name="os")
    private OrderScheduler scheduler;

    @Override
    public void contextInitialized(ServletContextEvent event) {

        try {
            scheduler.startJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


}
