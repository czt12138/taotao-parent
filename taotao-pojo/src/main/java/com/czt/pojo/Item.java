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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 商品表(TB_ITEM)
 * 
 * @author bianj
 * @version 1.0.0 2017-12-24
 */
@Entity
@Table(name = "TB_ITEM")
public class Item implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 5745309751649626943L;

    /** 商品id，同时也是商品编号 */
    @Id
    @Column(name = "ID", unique = true, nullable = false, length = 19)
    private Long id;

    /** 商品标题 */
    @Column(name = "TITLE", nullable = false, length = 100)
    private String title;

    /** 商品卖点 */
    @Column(name = "SELL_POINT", nullable = true, length = 500)
    private String sellPoint;

    /** 商品价格，单位为：分 */
    @Column(name = "PRICE", nullable = false, length = 19)
    private Long price;

    /** 库存数量 */
    @Column(name = "NUM", nullable = false, length = 10)
    private Integer num;

    /** 商品条形码 */
    @Column(name = "BARCODE", nullable = true, length = 30)
    private String barcode;

    /** 商品图片 */
    @Column(name = "IMAGE", nullable = true, length = 500)
    private String image;

    /** 所属类目，叶子类目 */
    @Column(name = "CID", nullable = false, length = 19)
    private Long cid;

    /** 商品状态，1-正常，2-下架，3-删除 */
    @Column(name = "STATUS", nullable = false, length = 3)
    private Integer status;

    /** 创建时间 */
    @Column(name = "CREATED", nullable = false)
    private Date created;

    /** 更新时间 */
    @Column(name = "UPDATED", nullable = false)
    private Date updated;

    public String[] getImages(){
        return image.split(",");
    }


    /**
     * 获取商品id，同时也是商品编号
     * 
     * @return 商品id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置商品id，同时也是商品编号
     * 
     * @param id
     *          商品id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取商品标题
     * 
     * @return 商品标题

     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 设置商品标题
     * 
     * @param title
     *          商品标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取商品卖点
     * 
     * @return 商品卖点
     */
    public String getSellPoint() {
        return this.sellPoint;
    }

    /**
     * 设置商品卖点
     * 
     * @param sellPoint
     *          商品卖点
     */
    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    /**
     * 获取商品价格，单位为：分
     * 
     * @return 商品价格
     */
    public Long getPrice() {
        return this.price;
    }

    /**
     * 设置商品价格，单位为：分
     * 
     * @param price
     *          商品价格
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * 获取库存数量
     * 
     * @return 库存数量
     */
    public Integer getNum() {
        return this.num;
    }

    /**
     * 设置库存数量
     * 
     * @param num
     *          库存数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取商品条形码
     * 
     * @return 商品条形码
     */
    public String getBarcode() {
        return this.barcode;
    }

    /**
     * 设置商品条形码
     * 
     * @param barcode
     *          商品条形码
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * 获取商品图片
     * 
     * @return 商品图片
     */
    public String getImage() {
        return this.image;
    }

    /**
     * 设置商品图片
     * 
     * @param image
     *          商品图片
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取所属类目，叶子类目
     * 
     * @return 所属类目
     */
    public Long getCid() {
        return this.cid;
    }

    /**
     * 设置所属类目，叶子类目
     * 
     * @param cid
     *          所属类目
     */
    public void setCid(Long cid) {
        this.cid = cid;
    }

    /**
     * 获取商品状态，1-正常，2-下架，3-删除
     * 
     * @return 商品状态
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置商品状态，1-正常，2-下架，3-删除
     * 
     * @param status
     *          商品状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     * 
     * @return 创建时间
     */
    public Date getCreated() {
        return this.created;
    }

    /**
     * 设置创建时间
     * 
     * @param created
     *          创建时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", sellPoint='" + sellPoint + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", barcode='" + barcode + '\'' +
                ", image='" + image + '\'' +
                ", cid=" + cid +
                ", status=" + status +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

    /**
     * 获取更新时间
     * 
     * @return 更新时间
     */
    public Date getUpdated() {
        return this.updated;
    }

    /**
     * 设置更新时间
     * 
     * @param updated
     *          更新时间
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}