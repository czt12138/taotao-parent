/*
 * Welcome to use the TableGo Tools.
 * 
 * http://vipbooks.iteye.com
 * http://blog.csdn.net/vipbooks
 * http://www.cnblogs.com/vipbooks
 * 
 * Author:bianj
 * Email:edinsker@163.com
 * Version:5.0.0
 */

package com.czt.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TB_ORDER
 * 
 * @author bianj
 * @version 1.0.0 2017-12-24
 */
@Entity
@Table(name = "TB_ORDER")
public class Order implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 2730461580580006510L;

    /** 订单id */
    @Id
    @Column(name = "ORDER_ID", unique = true, nullable = false, length = 50)
    private String orderId;

    /** 实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分 */
    @Column(name = "PAYMENT", nullable = true, length = 50)
    private String payment;

    /** 支付类型，1、在线支付，2、货到付款 */
    @Column(name = "PAYMENT_TYPE", nullable = true, length = 10)
    private Integer paymentType;

    /** 邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分 */
    @Column(name = "POST_FEE", nullable = true, length = 50)
    private String postFee;

    /** 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭 */
    @Column(name = "STATUS", nullable = true, length = 10)
    private Integer status;

    /** 订单创建时间 */
    @Column(name = "CREATE_TIME", nullable = true)
    private Date createTime;

    /** 订单更新时间 */
    @Column(name = "UPDATE_TIME", nullable = true)
    private Date updateTime;

    /** 付款时间 */
    @Column(name = "PAYMENT_TIME", nullable = true)
    private Date paymentTime;

    /** 发货时间 */
    @Column(name = "CONSIGN_TIME", nullable = true)
    private Date consignTime;

    /** 交易完成时间 */
    @Column(name = "END_TIME", nullable = true)
    private Date endTime;

    /** 交易关闭时间 */
    @Column(name = "CLOSE_TIME", nullable = true)
    private Date closeTime;

    /** 物流名称 */
    @Column(name = "SHIPPING_NAME", nullable = true, length = 20)
    private String shippingName;

    /** 物流单号 */
    @Column(name = "SHIPPING_CODE", nullable = true, length = 20)
    private String shippingCode;

    /** 用户id */
    @Column(name = "USER_ID", nullable = true, length = 19)
    private Long userId;

    /** 买家留言 */
    @Column(name = "BUYER_MESSAGE", nullable = true, length = 100)
    private String buyerMessage;

    /** 买家昵称 */
    @Column(name = "BUYER_NICK", nullable = true, length = 50)
    private String buyerNick;

    /** 买家是否已经评价 */
    @Column(name = "BUYER_RATE", nullable = true, length = 10)
    private Integer buyerRate;

    /**
     * 获取订单id
     * 
     * @return 订单id
     */
    public String getOrderId() {
        return this.orderId;
    }

    /**
     * 设置订单id
     * 
     * @param orderId
     *          订单id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
     * 
     * @return 实付金额。精确到2位小数;单位:元。如:200.07
     */
    public String getPayment() {
        return this.payment;
    }

    /**
     * 设置实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
     * 
     * @param payment
     *          实付金额。精确到2位小数;单位:元。如:200.07
     */
    public void setPayment(String payment) {
        this.payment = payment;
    }

    /**
     * 获取支付类型，1、在线支付，2、货到付款
     * 
     * @return 支付类型
     */
    public Integer getPaymentType() {
        return this.paymentType;
    }

    /**
     * 设置支付类型，1、在线支付，2、货到付款
     * 
     * @param paymentType
     *          支付类型
     */
    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * 获取邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
     * 
     * @return 邮费。精确到2位小数;单位:元。如:200.07
     */
    public String getPostFee() {
        return this.postFee;
    }

    /**
     * 设置邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
     * 
     * @param postFee
     *          邮费。精确到2位小数;单位:元。如:200.07
     */
    public void setPostFee(String postFee) {
        this.postFee = postFee;
    }

    /**
     * 获取状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
     * 
     * @return 状态：1、未付款
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
     * 
     * @param status
     *          状态：1、未付款
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取订单创建时间
     * 
     * @return 订单创建时间
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置订单创建时间
     * 
     * @param createTime
     *          订单创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取订单更新时间
     * 
     * @return 订单更新时间
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置订单更新时间
     * 
     * @param updateTime
     *          订单更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取付款时间
     * 
     * @return 付款时间
     */
    public Date getPaymentTime() {
        return this.paymentTime;
    }

    /**
     * 设置付款时间
     * 
     * @param paymentTime
     *          付款时间
     */
    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    /**
     * 获取发货时间
     * 
     * @return 发货时间
     */
    public Date getConsignTime() {
        return this.consignTime;
    }

    /**
     * 设置发货时间
     * 
     * @param consignTime
     *          发货时间
     */
    public void setConsignTime(Date consignTime) {
        this.consignTime = consignTime;
    }

    /**
     * 获取交易完成时间
     * 
     * @return 交易完成时间
     */
    public Date getEndTime() {
        return this.endTime;
    }

    /**
     * 设置交易完成时间
     * 
     * @param endTime
     *          交易完成时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取交易关闭时间
     * 
     * @return 交易关闭时间
     */
    public Date getCloseTime() {
        return this.closeTime;
    }

    /**
     * 设置交易关闭时间
     * 
     * @param closeTime
     *          交易关闭时间
     */
    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    /**
     * 获取物流名称
     * 
     * @return 物流名称
     */
    public String getShippingName() {
        return this.shippingName;
    }

    /**
     * 设置物流名称
     * 
     * @param shippingName
     *          物流名称
     */
    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    /**
     * 获取物流单号
     * 
     * @return 物流单号
     */
    public String getShippingCode() {
        return this.shippingCode;
    }

    /**
     * 设置物流单号
     * 
     * @param shippingCode
     *          物流单号
     */
    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }

    /**
     * 获取用户id
     * 
     * @return 用户id
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * 设置用户id
     * 
     * @param userId
     *          用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取买家留言
     * 
     * @return 买家留言
     */
    public String getBuyerMessage() {
        return this.buyerMessage;
    }

    /**
     * 设置买家留言
     * 
     * @param buyerMessage
     *          买家留言
     */
    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    /**
     * 获取买家昵称
     * 
     * @return 买家昵称
     */
    public String getBuyerNick() {
        return this.buyerNick;
    }

    /**
     * 设置买家昵称
     * 
     * @param buyerNick
     *          买家昵称
     */
    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    /**
     * 获取买家是否已经评价
     * 
     * @return 买家是否已经评价
     */
    public Integer getBuyerRate() {
        return this.buyerRate;
    }

    /**
     * 设置买家是否已经评价
     * 
     * @param buyerRate
     *          买家是否已经评价
     */
    public void setBuyerRate(Integer buyerRate) {
        this.buyerRate = buyerRate;
    }
}