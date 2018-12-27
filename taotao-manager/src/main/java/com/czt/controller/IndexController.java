package com.czt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.czt.pojo.Item;
import com.czt.service.ItemService;
import com.github.pagehelper.PageInfo;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.controller
 *  @文件名:   IndexController
 *  @创建者:   XuKu
 *  @创建时间:  2018/11/26 10:50
 *  @描述：    添加数据到索引库中
 */
@RestController
public class IndexController {

    @Autowired
    private SolrClient solrClient;

    @Reference
    private ItemService itemService ;

    @RequestMapping("initIndex")
    public  String index() throws IOException, SolrServerException {

        int page =1 ,rows = 500;

        do {

            List doclist = new ArrayList();
            PageInfo<Item> pageInfo = itemService.list(page,rows);
            List<Item> list = pageInfo.getList();
            for (Item item : list) {

                //一个文档对象就是一条索引数据
                SolrInputDocument doc = new SolrInputDocument();
                doc.addField("id", item.getId());
                doc.addField("item_title", item.getTitle());
                doc.addField("item_price", item.getPrice());
                doc.addField("item_image", item.getImage());
                doc.addField("item_cid", item.getCid());
                doc.addField("item_status", item.getStatus());
                doclist.add(doc);
            }

            solrClient.add(doclist);
            solrClient.commit();
            page++;
            rows = list.size();

        }while(rows == 500);

        return "success!!!";
    }
}
