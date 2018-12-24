package com.czt.quartz;

import com.czt.service.OrderService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.quartz
 *  @文件名:   OrderQuartz
 *  @创建者:   Czt
 *  @创建时间:  2018/12/20 12:40
 *  @描述：    用于配置 工作描述、核心调度器、触发器
 */
@Component
public class OrderQuartz {

    @Autowired
    private OrderService orderService;

    @Bean
    public JobDetail orderJob(){


        JobDataMap map = new JobDataMap();
        map.put("orderService",orderService);

        return  JobBuilder
                  .newJob(OrderJob.class)
                  .setJobData(map)
                  .withIdentity("clearOrder","order")
                  .build();
    }

    @Bean
    public Trigger orderTrigger(){

        ScheduleBuilder builder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");

        return TriggerBuilder
                .newTrigger()
                .withSchedule(builder)
                .startNow()
                .build();

    }

    @Bean
    public  Scheduler orderScheduler() throws SchedulerException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        return  scheduler;
    }


}
