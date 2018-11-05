package com.czt.service;

import com.czt.pojo.Content;
import com.github.pagehelper.PageInfo;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.service
 *  @文件名:   ContentService
 *  @创建者:   Administrator
 *  @创建时间:  2018/9/29 14:50
 *  @描述：    TODO
 */
public interface ContentService {

    int add(Content content);

    PageInfo<Content> list(long categoryId , int page , int rows);

    int edit(Content content);

    int delete(String ids);

    String  selectByCategoryId(long cid);
}
