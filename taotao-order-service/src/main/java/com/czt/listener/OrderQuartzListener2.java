package com.czt.listener;

import com.czt.quartz.OrderScheduler;
import org.quartz.SchedulerException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.listener
 *  @文件名:   OrderQuartzListener
 *  @创建者:   Xukun
 *  @创建时间:  2018/12/20 14:09
 *  @描述：    TODO
 */
@Component
public class OrderQuartzListener2 implements ApplicationListener<ApplicationReadyEvent>{

    @Resource(name="os")
    private OrderScheduler scheduler;



    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        System.out.println("ApplicationReadyEvent！！！");
        try {
            scheduler.startJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
