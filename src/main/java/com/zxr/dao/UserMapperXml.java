package com.zxr.dao;

import com.zxr.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author zhaoxiangrui
 * @create 2020/11/12 10:38
 */
public interface UserMapperXml {

	List<User> findAll();

	List<User> findByCondition(User user);

	void saveUser(User user);

	void deleteUser(Integer userId);

	void updateUser(User user);

	//多值查询 foreach
	List<User> findByIds(List<Integer> ids);

	List<User> findUserAndOrder();

	List<User> findUserAndRole();

}
