package com.czt.service;

import com.czt.pojo.Item;
import com.czt.pojo.Page;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.service
 *  @文件名:   SearchService
 *  @创建者:   Czt
 *  @创建时间:  2018/11/26 14:22
 *  @描述：    搜索接口类
 */
public interface SearchService {

   Page<Item> search(String q, int page, int pageSize);

   void addItem(String message);
}
