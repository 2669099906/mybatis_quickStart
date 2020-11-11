package com.zxr.dao;

import com.zxr.pojo.Order;
import com.zxr.pojo.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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

	/**
	 * private Integer id;
	 * 	private String username;
	 * 	private String password;
	 * 	private Date birthday;
	 * @return
	 */
	@Select("select * from user")
	@Results({
			@Result(id = true, property = "id", column = "id"),
			@Result(property = "username", column = "username"),
			@Result(property = "password", column = "password"),
			@Result(property = "birthday", column = "birthday"),
			@Result(property = "orders", column = "uid",
					javaType = List.class, many = @Many(select = "com.zxr.dao.OrderMapper."))
	})
	List<User> findAllUserAndOrder();

	List<User> findUserAndRole();

	@Select("select * from user where id = #{id}")
	User findById();
}
