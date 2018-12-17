package com.czt.pojo;

import java.io.Serializable;
import java.util.Date;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.pojo
 *  @文件名:   Cart
 *  @创建者:   Czt
 *  @创建时间:  2018/12/7 21:41
 *  @描述：    TODO
 */
public class Cart  implements Serializable{

     private  Long id;
     private  Long userId;
    private  Long itemId;
    private  String itemTitle;
    private  String itemImage;
    private  Long itemPrice;
    private  Integer num;
    private Date create;
    private  Date update;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public Long getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Long itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        return itemId != null ? itemId.equals(cart.itemId) : cart.itemId == null;
    }

    @Override
    public int hashCode() {
        return itemId != null ? itemId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userId=" + userId +
                ", itemId=" + itemId +
                ", itemTitle='" + itemTitle + '\'' +
                ", itemImage='" + itemImage + '\'' +
                ", itemPrice=" + itemPrice +
                ", num=" + num +
                ", create=" + create +
                ", update=" + update +
                '}';
    }
}
