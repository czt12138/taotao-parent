package com.czt.service;

import com.czt.pojo.Order;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.service
 *  @文件名:   OrderService
 *  @创建者:   Czt
 *  @创建时间:  2018/12/19 16:11
 *  @描述：    提交订单
 */
public interface OrderService {

    /**
     * 提交订单
     * @param order,userId
     * @return id
     */
    void  addOrder(Order order,String orderId);

    /**
     * 通过id查询订单信息
     * @param id
     * @return
     */
    Order findOrderById(String id);


    /**
     * 清除无效订单
     */
    void clearOrder();

}
