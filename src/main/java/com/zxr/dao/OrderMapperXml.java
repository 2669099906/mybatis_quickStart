package com.zxr.dao;

import com.zxr.pojo.Order;

import java.util.List;

/**
 * @Author zhaoxiangrui
 * @create 2020/11/12 10:37
 */
public interface OrderMapperXml {

	List<Order> findOrderAndUser();

}
