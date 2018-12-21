package com.czt.controller;

import com.czt.util.UploadUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.controller
 *  @文件名:   UploadController
 *  @创建者:  czt
 *  @创建时间:  2018/9/26 17:08
 *  @描述：   文件上传
 */
@Controller

public class UploadController {

    /***
     * 用于处理JSON转换问题
     */
    @Autowired
    private ObjectMapper objectMapper;

    @ResponseBody
    @RequestMapping(value="/rest/pic/upload",method= RequestMethod.POST)
    public String upload(@RequestParam(value="uploadFile")MultipartFile file, HttpSession session) throws Exception{


        //获取文件后缀
        String subfix = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");


        //获取resources 地址路径
        String path = System.getProperty("user.dir")+"/src/main/resources/";


        //执行文件上传
        String[] uploadinfos = UploadUtil.upload(path+"tracker.conf", file.getBytes(), subfix);

        for (String string : uploadinfos) {
            System.out.println(string);
        }
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("error", 0);
        map.put("url", "http://image2.taotao.com/"+uploadinfos[0]+"/"+uploadinfos[1]);
        map.put("height", 100);
        map.put("width", 100);

        //将Map对象转成JSON字符串
        String json = objectMapper.writeValueAsString(map);

        return json;
    }




}