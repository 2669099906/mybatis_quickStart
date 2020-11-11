package com.zxr.dao;

import com.zxr.pojo.Order;
import com.zxr.pojo.User;

import java.util.List;

/**
 * @Author zhaoxiangrui
 * @create 2020/11/9 15:49
 */
public interface UserDao {

	List<User> findAll();

	List<User> findByCondition(User user);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUser(Integer userId);

	//多值查询 foreach
	List<User> findByIds(List<Integer> ids);

	List<User> findUserAndOrder();

	List<User> findUserAndRole();
}
