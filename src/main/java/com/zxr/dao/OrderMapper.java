package com.zxr.dao;

import com.zxr.pojo.Order;
import com.zxr.pojo.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhaoxiangrui
 * @date 2020/11/10 22:23
 */
public interface OrderMapper {

    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "total", column = "total"),
            @Result(property = "user", column = "uid",
                    javaType = User.class,
                    one = @One(select = "com.zxr.dao.UserMapper.findById"))
    })
    List<Order> findAll();

    @Select("select * from orders where uid = #{uid}")
    List<Order> findByUserId(Integer uid);

}
