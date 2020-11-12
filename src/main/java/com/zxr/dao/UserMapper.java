package com.zxr.dao;

import com.zxr.pojo.Order;
import com.zxr.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.mybatis.caches.redis.RedisCache;

import java.util.List;

/**
 * @Author zhaoxiangrui
 * @create 2020/11/9 15:49
 */
//可以添加Cache的实现类
@CacheNamespace(implementation = RedisCache.class) //开启二级缓存
public interface UserMapper {


	@Update("update user set username=#{username} where id=#{id}")
	void updateUser(User user);


	//添加用户
	@Insert("insert into user values(#{id}, #{username}, #{password}, #{birthday})")
	void addUser(User user);


	@Select("select * from user")
	@Results({
			@Result(id = true, property = "id", column = "id"),
			@Result(property = "username", column = "username"),
			@Result(property = "password", column = "password"),
			@Result(property = "birthday", column = "birthday"),
			@Result(property = "orderList", column = "id",
					javaType = List.class, many = @Many(select = "com.zxr.dao.OrderMapper.findByUserId"))
	})
	List<User> findAllUserAndOrder();


//	@Options(useCache = false)//标识当前方法都从数据库进行查询
	@Select("select * from user where id = #{id}")
	User findById(Integer userId);

	@Select("select * from user")
	@Results({
			@Result(id = true, property = "id", column = "id"),
			@Result(property = "username", column = "username"),
			@Result(property = "roles", column = "id",
					javaType = List.class, many = @Many(select = "com.zxr.dao.RoleMapper.selectRoleByUserId"))
	})
	List<User> findAllUserAndRole();
}
