package com.zxr.dao;

import com.zxr.pojo.Order;
import com.zxr.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author zhaoxiangrui
 * @create 2020/11/9 15:49
 */
public interface UserDao {

	List<User> findAll();

	List<User> findByCondition(User user);

	void saveUser(User user);

	@Update("update user set username=#{username} where id=#{id}")
	void updateUser(User user);

	void deleteUser(Integer userId);

	//多值查询 foreach
	List<User> findByIds(List<Integer> ids);

	List<User> findUserAndOrder();

	//添加用户
	@Insert("insert into user values(#{id}, #{username})")
	void addUser(User user);


	@Select("select * from user")
	@Results({
			@Result(id = true, property = "id", column = "id"),
			@Result(property = "username", column = "username"),
			@Result(property = "password", column = "password"),
			@Result(property = "birthday", column = "birthday"),
			@Result(property = "orderList", column = "uid",
					javaType = List.class, many = @Many(select = "com.zxr.dao.OrderMapper.findByUserId"))
	})
	List<User> findAllUserAndOrder();

	List<User> findUserAndRole();

	@Select("select * from user where id = #{id}")
	User findById(Integer userId);
}
