package com.czt.controller.freemarker;

import com.alibaba.dubbo.config.annotation.Reference;
import com.czt.pojo.Item;
import com.czt.pojo.ItemDesc;
import com.czt.service.ItemDescService;
import com.czt.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;


/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.controller.service
 *  @文件名:   FreeMarkerService
 *  @创建者:   Czt
 *  @创建时间:  2018/12/7 15:08
 *  @描述：    TODO
 */
@Component //交给spring托管，才可以调用这个方法
public class FreeMarkerService {

    @Reference
    private ItemService itemService;

    @Reference
    private ItemDescService itemDescService;

    //后台一新增商品就会执行，用于创建商品详情页的静态页面
    @JmsListener(destination = "item-save")
    public  void  addItem(String message) throws Exception {

        System.out.println("商品详情页收到的消息是：" + message);
         Item item = itemService.findItemById(Long.parseLong(message));
        ItemDesc itemdesc = itemDescService.findDescById(Long.parseLong(message));

        //创建配置对象
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_25);

        String path = System.getProperty("user.dir")+"/src/main/webapp/ftl";
        //设置加载模板的文件路径
        configuration.setDirectoryForTemplateLoading(new File(path));
        //获取模板
        Template template = configuration.getTemplate("item.ftl");

        //准备数据
        Map<String,Object> root = new HashMap();

        root.put("item",item);
        root.put("itemDesc",itemdesc);

        //静态页面存放的位置
        Writer out = new FileWriter("E:/taotao/item/"+message+".html");
        //输出页面
        template.process(root,out);

        out.close();

    }
}
