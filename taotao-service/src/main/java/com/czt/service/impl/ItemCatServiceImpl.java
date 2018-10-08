package com.czt.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.czt.mapper.ItemCatMapper;
import com.czt.pojo.ItemCat;
import com.czt.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.service.impl
 *  @文件名:   ItemCatServiceImpl
 *  @创建者:   Administrator
 *  @创建时间:  2018/9/25 10:19
 *  @描述：    TODO
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public List<ItemCat> getCategoryByParentId(long parentId) {



        //按照普通列来查询。
        ItemCat itemCat = new ItemCat();

        //long val = Long.parseLong(parentId+"");
        itemCat.setParentId(parentId);

        //selectByExample 里面使用的是criteria 查询条件对象。
        return   itemCatMapper.select(itemCat);

       /* Example example = new Example(ItemCat.class);
        example.createCriteria().andEqualTo("username","zhangsan");
        itemCatMapper.selectByExample(example);*/

        //想按照学生的姓名来查询
        /*Student  stu = new Student();
        stu.setName("张三")
        itemCatMapper.select(Student);*/


    }
}
