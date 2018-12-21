package com.czt.quartz;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.quartz
 *  @文件名:   OrderScheduler
 *  @创建者:   Czt
 *  @创建时间:  2018/12/20 14:03
 *  @描述：    开启、停止、删除订单
 */
@Component("os")
public class OrderScheduler {

    @Autowired
    private Scheduler orderScheduler;

    @Autowired
    private JobDetail orderJob;

    @Autowired
    private Trigger orderTrigger;

     public  void  startJob() throws SchedulerException {

          orderScheduler.scheduleJob(orderJob,orderTrigger);
          orderScheduler.start();


         }

    public  void  pauseJob() throws SchedulerException {

          JobKey key = new JobKey("clearOrder","order");
          orderScheduler.pauseJob(key);

    }
    public  void  deleteJob() throws SchedulerException {


        JobKey key = new JobKey("clearOrder","order");
        orderScheduler.deleteJob(key);

    }



}
