package com.czt.mapper;


import com.czt.pojo.ContentCategory;
import tk.mybatis.mapper.common.Mapper;

//为了把mapper交給Spring管理，能被实例化
@org.apache.ibatis.annotations.Mapper
public interface ContentCategoryMapper  extends Mapper<ContentCategory> {
}