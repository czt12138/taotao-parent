package com.czt.mapper;

import com.czt.pojo.User;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {


//如果有自定义的方法，那么需要有对应的sql语句
//如果觉得通用mapper的方法已经足够开发了，那么也可以不用自定义。
//addMyUser()
}