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
 * TB_CONTENT
 * 
 * @author bianj
 * @version 1.0.0 2017-12-24
 */
@Entity
@Table(name = "TB_CONTENT")
public class Content implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -8496488120916531724L;

    /**  */
    @Id
    @Column(name = "ID", unique = true, nullable = false, length = 19)
    private Long id;

    /** 内容类目ID */
    @Column(name = "CATEGORY_ID", nullable = false, length = 19)
    private Long categoryId;

    /** 内容标题 */
    @Column(name = "TITLE", nullable = true, length = 200)
    private String title;

    /** 子标题 */
    @Column(name = "SUB_TITLE", nullable = true, length = 100)
    private String subTitle;

    /** 标题描述 */
    @Column(name = "TITLE_DESC", nullable = true, length = 500)
    private String titleDesc;

    /** 链接 */
    @Column(name = "URL", nullable = true, length = 500)
    private String url;

    /** 图片绝对路径 */
    @Column(name = "PIC", nullable = true, length = 300)
    private String pic;

    /** 图片2 */
    @Column(name = "PIC2", nullable = true, length = 300)
    private String pic2;

    /** 内容 */
    @Column(name = "CONTENT", nullable = true, length = 65535)
    private String content;

    /**  */
    @Column(name = "CREATED", nullable = true)
    private Date created;

    /**  */
    @Column(name = "UPDATED", nullable = true)
    private Date updated;

    /**
     * 获取
     * 
     * @return 
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取内容类目ID
     * 
     * @return 内容类目ID
     */
    public Long getCategoryId() {
        return this.categoryId;
    }

    /**
     * 设置内容类目ID
     * 
     * @param categoryId
     *          内容类目ID
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取内容标题
     * 
     * @return 内容标题
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 设置内容标题
     * 
     * @param title
     *          内容标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取子标题
     * 
     * @return 子标题
     */
    public String getSubTitle() {
        return this.subTitle;
    }

    /**
     * 设置子标题
     * 
     * @param subTitle
     *          子标题
     */
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    /**
     * 获取标题描述
     * 
     * @return 标题描述
     */
    public String getTitleDesc() {
        return this.titleDesc;
    }

    /**
     * 设置标题描述
     * 
     * @param titleDesc
     *          标题描述
     */
    public void setTitleDesc(String titleDesc) {
        this.titleDesc = titleDesc;
    }

    /**
     * 获取链接
     * 
     * @return 链接
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * 设置链接
     * 
     * @param url
     *          链接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取图片绝对路径
     * 
     * @return 图片绝对路径
     */
    public String getPic() {
        return this.pic;
    }

    /**
     * 设置图片绝对路径
     * 
     * @param pic
     *          图片绝对路径
     */
    public void setPic(String pic) {
        this.pic = pic;
    }

    /**
     * 获取图片2
     * 
     * @return 图片2
     */
    public String getPic2() {
        return this.pic2;
    }

    /**
     * 设置图片2
     * 
     * @param pic2
     *          图片2
     */
    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    /**
     * 获取内容
     * 
     * @return 内容
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 设置内容
     * 
     * @param content
     *          内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取
     * 
     * @return 
     */
    public Date getCreated() {
        return this.created;
    }

    /**
     * 设置
     * 
     * @param created
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * 获取
     * 
     * @return 
     */
    public Date getUpdated() {
        return this.updated;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", titleDesc='" + titleDesc + '\'' +
                ", url='" + url + '\'' +
                ", pic='" + pic + '\'' +
                ", pic2='" + pic2 + '\'' +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

    /**
     * 设置
     * 
     * @param updated
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}