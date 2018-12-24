package com.czt.quartz;

import com.czt.service.OrderService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.quartz
 *  @文件名:   OrderJob
 *  @创建者:   Czt
 *  @创建时间:  2018/12/20 12:39
 *  @描述：    清除工作
 */


public class OrderJob extends QuartzJobBean{


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        System.out.println("开始清除无效的订单了！！！");

       // ApplicationContext context2 = (ApplicationContext) context.getJobDetail().getJobDataMap().get("applicationContext");

       // System.out.println("容器为：" + context2);

       OrderService orderService = (OrderService) context.getJobDetail().getJobDataMap().get("orderService");

       orderService.clearOrder();

    }
}
