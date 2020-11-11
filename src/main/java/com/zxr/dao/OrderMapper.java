package com.zxr.dao;

import com.zxr.pojo.Order;
import com.zxr.pojo.User;

import java.util.List;

/**
 * @author zhaoxiangrui
 * @date 2020/11/10 22:23
 */
public interface OrderMapper {

    List<Order> findOrderAndUser();

}
