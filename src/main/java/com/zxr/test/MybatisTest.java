package com.zxr.test;


import com.zxr.dao.UserDao;
import com.zxr.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author zhaoxiangrui
 * @create 2020/11/9 16:13
 */
public class MybatisTest {

	@Test
	public void test() throws Exception {
		//1.加载配置文件，加载为输入流
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.解析配置文件，创建sqlSessionFactory工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		//3.生产sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession(true);//开启一个事务，默认不默认提交
																				//增删改时需要手动提交
																				//
		//4.调用selectList查询多条数据，selectOne查询单条数据，update更新数据 insert添加数据 delete 删除数据
		List<User> userList = sqlSession.selectList(UserDao.class.getName() + "." + "findAll");
		System.out.println(userList);
//		User user = new User();
//		user.setId(5);
//		user.setUsername("tom");
//		sqlSession.insert(UserDao.class.getName() +"."+ "saveUser", user);
//		sqlSession.commit();
//		userList = sqlSession.selectList(UserDao.class.getName() + "." + "findAll");
//		System.out.println(userList);
//		sqlSession.update(UserDao.class.getName() +"."+ "updateUser", user);
		User user = new User();
		user.setId(4);
		user.setUsername("jack");
		sqlSession.delete(UserDao.class.getName() +"."+ "deleteUser", user);
		userList = sqlSession.selectList(UserDao.class.getName() + "." + "findAll");
		System.out.println(userList);
		sqlSession.close();
	}

	@Test
	public void test02() throws IOException {
		//1.加载配置文件，加载为输入流
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.解析配置文件，创建sqlSessionFactory工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		//3.生产sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession(true);//开启一个事务，默认不默认提交
		//增删改时需要手动提交
		//
		UserDao userMapper = sqlSession.getMapper(UserDao.class);
		List<User> all = userMapper.findAll();
		System.out.println(all);
		User user = new User();
		user.setId(4);
		user.setUsername("jack");
		userMapper.updateUser(user);
		all = userMapper.findAll();
		System.out.println(all);

	}

	@Test
	public void test03() throws IOException {
		//1.加载配置文件，加载为输入流
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.解析配置文件，创建sqlSessionFactory工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		//3.生产sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession(true);//开启一个事务，默认不默认提交
		//增删改时需要手动提交
		//
		UserDao userMapper = sqlSession.getMapper(UserDao.class);
		User user = new User();
		user.setId(6);
		List<User> byCondition = userMapper.findByCondition(user);
		System.out.println(byCondition);
	}
}
