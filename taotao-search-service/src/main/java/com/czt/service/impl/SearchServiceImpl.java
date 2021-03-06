package com.czt.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.czt.mapper.ItemMapper;
import com.czt.pojo.Item;
import com.czt.pojo.Page;
import com.czt.service.SearchService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.service.impl
 *  @文件名:   SearchServiceImpl
 *  @创建者:   Czt
 *  @创建时间:  2018/11/26 14:26
 *  @描述：    搜索商品
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public Page<Item> search(String q,int page,int pageSize) {

        try {


            SolrQuery s = new SolrQuery();

            //设置高亮显示
            s.setHighlight(true);
            //设置域
            s.addHighlightField("item_title");
            //设置前缀后缀
            s.setHighlightSimplePre("<font color='red'>");
            s.setHighlightSimplePost("</font>");


            //设置参数
            s.setQuery("item_title:"+q);

            s.setStart((page - 1)*pageSize); //跳过的条数
            s.setRows(pageSize);

            QueryResponse query = solrClient.query(s);

            //获取查询结果
            SolrDocumentList results = query.getResults();

            //集合数据
            List<Item>  list = new ArrayList<>();
            //获取高亮的数据
            Map<String, Map<String, List<String>>> map = query.getHighlighting();

            for (SolrDocument document : results) {

                Item item = new Item();

                long id = Long.parseLong((String) document.getFieldValue("id"));

                String item_title = (String)document.getFieldValue("item_title");

                item.setId(id);

                Map<String, List<String>> map1 = map.get(id+"");

                List<String> highLightList = map1.get("item_title");

                //表示这个商品有高亮的标题
                if(highLightList !=null && highLightList.size() > 0 ){
                    item_title = highLightList.get(0);
                }

                //高亮显示关键字
                item.setTitle(item_title );




                item.setPrice((Long) document.getFieldValue("item_price"));
                item.setImage((String) document.getFieldValue("item_image"));
                item.setCid((Long) document.getFieldValue("item_cid"));

                list.add(item);
            }

            //封装page对象的属性
            Page<Item> pagelist = new Page<Item>(results.getNumFound(),page,pageSize);
            pagelist.setList(list);


           return  pagelist;


        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @JmsListener(destination = "item-save")
    @Override
    public void addItem(String message) {
        try {
            System.out.println("搜索系统收到的消息是:  " + message);

            //1. 查询最新添加的那件商品
            Item item = itemMapper.selectByPrimaryKey(Long.parseLong(message));

            //2. 把商品添加到索引库中

            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("id",message);
            doc.addField("item_title",item.getTitle());
            doc.addField("item_image",item.getImage());
            doc.addField("item_cid",item.getCid());
            doc.addField("item_price",item.getPrice());
            doc.addField("item_status",item.getStatus());

            solrClient.add(doc);
            solrClient.commit();

            System.out.println("索引库更新完毕" + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
