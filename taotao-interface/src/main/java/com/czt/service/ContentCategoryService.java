package com.czt.service;

import com.czt.pojo.ContentCategory;

import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.controller
 *  @文件名:   ContentCategoryService
 *  @创建者:   Czt
 *  @创建时间:  2018/9/28 8:50
 *  @描述：    内容分类接口类
 */
public interface ContentCategoryService {

  List<ContentCategory> getCategoryByParentId(Long id);

  ContentCategory add(ContentCategory contentCategory);

  int update(ContentCategory contentCategory);

  int delete(ContentCategory contentCategory);
}
