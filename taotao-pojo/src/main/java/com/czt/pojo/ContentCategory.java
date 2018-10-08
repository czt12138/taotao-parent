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

import javax.persistence.*;
import java.util.Date;

/**
 * 内容分类(TB_CONTENT_CATEGORY)
 * 
 * @author bianj
 * @version 1.0.0 2017-12-24
 */
@Entity
@Table(name = "TB_CONTENT_CATEGORY")
public class ContentCategory implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 2074593403386106954L;

    /** 类目ID */
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "ID", unique = true, nullable = false, length = 19)
    private Long id;

    /** 父类目ID=0时，代表的是一级的类目 */
    @Column(name = "PARENT_ID", nullable = true, length = 19)
    private Long parentId;

    /** 分类名称 */
    @Column(name = "NAME", nullable = true, length = 50)
    private String name;

    /** 状态。可选值:1(正常),2(删除) */
    @Column(name = "STATUS", nullable = true, length = 10)
    private Integer status;

    /** 排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数 */
    @Column(name = "SORT_ORDER", nullable = true, length = 10)
    private Integer sortOrder;

    /** 该类目是否为父类目，1为true，0为false */
    @Column(name = "IS_PARENT", nullable = true)
    private Boolean isParent;

    /** 创建时间 */
    @Column(name = "CREATED", nullable = true)
    private Date created;

    /** 创建时间 */
    @Column(name = "UPDATED", nullable = true)
    private Date updated;

    /**
     * 获取类目ID
     * 
     * @return 类目ID
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置类目ID
     * 
     * @param id
     *          类目ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取父类目ID=0时，代表的是一级的类目
     * 
     * @return 父类目ID=0时
     */
    public Long getParentId() {
        return this.parentId;
    }

    /**
     * 设置父类目ID=0时，代表的是一级的类目
     * 
     * @param parentId
     *          父类目ID=0时
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取分类名称
     * 
     * @return 分类名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置分类名称
     * 
     * @param name
     *          分类名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取状态。可选值:1(正常),2(删除)
     * 
     * @return 状态。可选值:1(正常)
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置状态。可选值:1(正常),2(删除)
     * 
     * @param status
     *          状态。可选值:1(正常)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
     * 
     * @return 排列序号
     */
    public Integer getSortOrder() {
        return this.sortOrder;
    }

    /**
     * 设置排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
     * 
     * @param sortOrder
     *          排列序号
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * 获取该类目是否为父类目，1为true，0为false
     * 
     * @return 该类目是否为父类目
     */
    public Boolean getIsParent() {
        return this.isParent;
    }

    /**
     * 设置该类目是否为父类目，1为true，0为false
     * 
     * @param isParent
     *          该类目是否为父类目
     */
    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
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

    /**
     * 获取创建时间
     * 
     * @return 创建时间
     */
    public Date getUpdated() {
        return this.updated;
    }

    /**
     * 设置创建时间
     * 
     * @param updated
     *          创建时间
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }


    public String getText(){
        return name;
    }

    public String getState(){
        return isParent?"closed":"open";
    }


}