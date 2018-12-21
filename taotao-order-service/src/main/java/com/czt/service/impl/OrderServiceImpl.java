package com.czt.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.czt.mapper.OrderItemMapper;
import com.czt.mapper.OrderMapper;
import com.czt.mapper.OrderShippingMapper;
import com.czt.pojo.Order;
import com.czt.pojo.OrderItem;
import com.czt.pojo.OrderShipping;
import com.czt.service.OrderService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.service.impl
 *  @文件名:   OrderServiceImpl
 *  @创建者:   Czt
 *  @创建时间:  2018/12/19 16:14
 *  @描述：    TODO
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderShippingMapper orderShippingMapper;

    @Override
    public void addOrder(Order order, String orderId) {

        //添加订单记录
        order.setOrderId(orderId);
        order.setStatus(1); //未付款
        order.setCreateTime(new Date());
        order.setUpdateTime(order.getCreateTime());

        orderMapper.insertSelective(order);

        //添加订单条目记录
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {

            orderItem.setId(UUID.randomUUID().toString().replaceAll("-",""));
            orderItem.setOrderId(orderId);
            orderItemMapper.insertSelective(orderItem);
        }

        //添加物流信息
        OrderShipping orderShipping = order.getOrderShipping();
        orderShipping.setOrderId(orderId);
        orderShipping.setCreated(new Date());
        orderShipping.setUpdated(order.getCreateTime());
        orderShippingMapper.insertSelective(orderShipping);

    }

    @Override
    public Order findOrderById(String id) {

        //根据Id查询订单表信息
        Order order = orderMapper.selectByPrimaryKey(id);

        //查询订单条目表信息
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(id);
        List<OrderItem> orderItems = orderItemMapper.select(orderItem);
        order.setOrderItems(orderItems);

        //查询物流信息
        OrderShipping shipping = orderShippingMapper.selectByPrimaryKey(id);
        order.setOrderShipping(shipping);

        return  order;

    }

    @Override
    public void clearOrder() {


        System.out.println("现在去清除无效的订单了！！");
       Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status",1);
        criteria.andEqualTo("paymentType",1);
        criteria.andEqualTo("createTime",new DateTime().minusDays(2).toDate());

        Order order = new Order();
        order.setStatus(6);
        order.setCloseTime(new Date());
        System.out.println("订单信息："+order);
        orderMapper.updateByExampleSelective(order,example);
    }
}
