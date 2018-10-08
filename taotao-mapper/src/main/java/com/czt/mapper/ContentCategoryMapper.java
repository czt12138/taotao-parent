package com.czt.mapper;


import com.czt.pojo.ContentCategory;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
//import com.github.abel533.mapper.Mapper;  //独立使用的，没有使用Springboot
public interface ContentCategoryMapper  extends Mapper<ContentCategory> {
}