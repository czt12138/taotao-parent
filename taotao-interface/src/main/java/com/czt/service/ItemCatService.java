package com.czt.service;

import com.czt.pojo.ItemCat;

import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.service
 *  @文件名:   ItemCatService
 *  @创建者:   czt
 *  @创建时间:  2018/9/25 10:18
 *  @描述：     商品分类接口
 */
public interface ItemCatService {

    List<ItemCat> getCategoryByParentId(long parentId);
}
