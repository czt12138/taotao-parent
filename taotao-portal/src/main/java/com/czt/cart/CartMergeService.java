package com.czt.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.czt.cart
 *  @文件名:   CartMergeService
 *  @创建者:   Czt
 *  @创建时间:  2018/12/13 20:48
 *  @描述：    TODO
 */
public interface CartMergeService {

     void mergeCart(String ticket, HttpServletRequest request, HttpServletResponse response);
}
