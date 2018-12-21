package com.czt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.czt.pojo.Item;
import com.czt.pojo.Page;
import com.czt.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.controller
 *  @文件名:   PageController
 *  @创建者:   Czt
 *  @创建时间:  2018/11/26 9:52
 *  @描述：    查询索引库数据并显示
 */
@Controller
public class SearchController {


    @Reference
    private SearchService searchService;

    @RequestMapping("search.shtml")
    public String search(String q, @RequestParam(defaultValue = "1") int page, Model model) {

        int pageSize = 16;

        //查询索引库中的数据并显示在搜索页面
        Page<Item> pageInfo = searchService.search(q, page, pageSize);

        //把数据存进model里去，用于前端显示
        model.addAttribute("page",pageInfo);
        model.addAttribute("query",q);
        model.addAttribute("totalPages",pageInfo.getLast());

        return  "search";

    }
}
