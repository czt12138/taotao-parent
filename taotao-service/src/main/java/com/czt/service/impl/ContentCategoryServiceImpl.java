package com.czt.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.czt.mapper.ContentCategoryMapper;
import com.czt.pojo.ContentCategory;
import com.czt.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.service.impl
 *  @文件名:   ContentCategoryServiceImpl
 *  @创建者:   Czt
 *  @创建时间:  2018/9/28 8:52
 *  @描述：    内容分类实现类
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
     private ContentCategoryMapper  mapper;

    @Override
    public List<ContentCategory> getCategoryByParentId(Long id) {

        ContentCategory  category   = new ContentCategory();

        category.setParentId(id);

        return mapper.select(category);


    }

    @Override
    public ContentCategory add(ContentCategory contentCategory) {

        //1.直接添加这个分类到表里  contentCategory: parentId  name

        contentCategory.setStatus(1);  //正常使用
        contentCategory.setIsParent(false); //不是父亲分类
        contentCategory.setCreated(new Date());//创建时间
        contentCategory.setUpdated(new Date()); //更新时间

        mapper.insert(contentCategory);


        //判断当前分类的父亲是不是子分类，如果是，把它变成父级分类。

        Long parentId = contentCategory.getParentId();
        ContentCategory parentcategory = mapper.selectByPrimaryKey(parentId);

        //判断它的父亲是不是子分类
         if(!parentcategory.getIsParent()){

             //让它的父亲是父级分类
             parentcategory.setIsParent(true);

         }

         mapper.updateByPrimaryKeySelective(parentcategory);
        System.out.println("contentCategory=" + contentCategory);

        return  contentCategory;

    }

    @Override
    public int update(ContentCategory contentCategory) { //参数只有id和name,剩下都是默认值

        int rows = mapper.updateByPrimaryKeySelective(contentCategory);

        System.out.println("rows=" + rows);

        return rows;
    }

    @Override
    public int delete(ContentCategory contentCategory) {


        //考虑删除父级分类

        //定义一个集合，装删除的对象
        List<ContentCategory> list = new ArrayList<>();

        //根据当前删除的节点id，找到所有孩子

        //先存当前本来应删除的分类
        list.add(contentCategory);

        //通过id查询它的子级分类
        findAllChild(list,contentCategory.getId());

        //再去删除,list集合已完全装好
        int result = deleteAll(list);

        //按照parentid去查询总数。

        ContentCategory c = new ContentCategory();
        c.setParentId(contentCategory.getParentId());
        int count = mapper.selectCount(c);

        //表示当前操作的这个节点的父亲，下面没有子节点
        if (count<1){

            //这里还要执行一次更新的操作，所以还需要再创建一次对象。
        c = new ContentCategory();
        c.setId(contentCategory.getId());
        c.setIsParent(false);
        mapper.updateByPrimaryKeySelective(c);



        }
        return result;
    }

    /**
     * 删除一个集合
     * @param list
     * @return
     */

    private int deleteAll(List<ContentCategory> list) {

        int result = 0;
        for (ContentCategory category : list) {

            result += mapper.delete(category);
        }
        return  result;
    }

    /**
     * 查询给定的分类id的所有子分类，包含多重的子级分类
     * @param list 存储的集合
     * @param id   当前要查询的Id
     */
    private void findAllChild(List<ContentCategory> list, Long id) {

        //找到当前的节点孩子
        List<ContentCategory> childelist = getCategoryByParentId(id);

        if (childelist != null & childelist.size()>0 ){

            //遍历这些字级分类
            for (ContentCategory  category:childelist){

                //先往list集合添加这个分类
                list.add(category);


                //执行递归,再调用方法
                findAllChild(list,category.getId());
            }

        }

    }
}
