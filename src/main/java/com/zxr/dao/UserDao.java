package com.zxr.dao;

import com.zxr.pojo.User;

import java.util.List;

/**
 * @Author zhaoxiangrui
 * @create 2020/11/9 15:49
 */
public interface UserDao {

	public List<User> findAll();

	public List<User> findByCondition(User user);

	public void saveUser(User user);

	public void updateUser(User user);

	public void deleteUser(Integer userId);
}
