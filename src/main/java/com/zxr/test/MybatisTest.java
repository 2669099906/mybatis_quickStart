package com.zxr.test;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxr.dao.*;
import com.zxr.pojo.Order;
import com.zxr.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author zhaoxiangrui
 * @create 2020/11/9 16:13
 */
public class MybatisTest {

	private UserMapperXml userMapperXml;
	private UserMapper userMapper;
	private OrderMapper orderMapper;
	private OrderMapperXml orderMapperXml;
	private AutoUserMapper autoUserMapper;

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
		List<User> userList = sqlSession.selectList(UserMapperXml.class.getName() + "." + "findAll");
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
		sqlSession.delete(UserMapperXml.class.getName() +"."+ "deleteUser", user);
		userList = sqlSession.selectList(UserMapperXml.class.getName() + "." + "findAll");
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
		UserMapperXml userMapper = sqlSession.getMapper(UserMapperXml.class);
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
		UserMapperXml userMapper = sqlSession.getMapper(UserMapperXml.class);
		User user = new User();
		user.setId(1);
		List<User> byCondition = userMapper.findByCondition(user);
		System.out.println(byCondition);
	}

	@Test
	public void test04() throws IOException {
		//1.加载配置文件，加载为输入流
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.解析配置文件，创建sqlSessionFactory工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		//3.生产sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession(true);//开启一个事务，默认不默认提交
		//增删改时需要手动提交
		UserMapperXml mapper = sqlSession.getMapper(UserMapperXml.class);
		List<User> byIds = mapper.findByIds(Arrays.asList(1, 2, 4));
		System.out.println(byIds);
	}

	@Test
	public void test05() throws IOException {
		//1.加载配置文件，加载为输入流
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.解析配置文件，创建sqlSessionFactory工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		//3.生产sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession(true);//开启一个事务，默认不默认提交
		//增删改时需要手动提交
		OrderMapperXml mapper = sqlSession.getMapper(OrderMapperXml.class);
		List<Order> orderAndUser = mapper.findOrderAndUser();
		System.out.println(orderAndUser);
	}

	@Test
	public void test06() throws IOException {
		//1.加载配置文件，加载为输入流
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.解析配置文件，创建sqlSessionFactory工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		//3.生产sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession(true);//开启一个事务，默认不默认提交
		//增删改时需要手动提交
		UserMapperXml mapper = sqlSession.getMapper(UserMapperXml.class);
		List<User> userAndOrder = mapper.findUserAndOrder();
		System.out.println(userAndOrder);
	}

	@Test
	public void test07() throws IOException {
		//1.加载配置文件，加载为输入流
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.解析配置文件，创建sqlSessionFactory工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		//3.生产sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession(true);//开启一个事务，默认不默认提交
		//增删改时需要手动提交
		UserMapperXml mapper = sqlSession.getMapper(UserMapperXml.class);
		List<User> userAndRole = mapper.findUserAndRole();
		System.out.println(userAndRole);
	}

	@BeforeEach
	public void before() throws IOException {
		//1.加载配置文件，加载为输入流
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.解析配置文件，创建sqlSessionFactory工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		//3.生产sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession(true);//开启一个事务，默认不默认提交
		//增删改时需要手动提交
		userMapperXml = sqlSession.getMapper(UserMapperXml.class);
		userMapper = sqlSession.getMapper(UserMapper.class);
		orderMapper = sqlSession.getMapper(OrderMapper.class);
		orderMapperXml = sqlSession.getMapper(OrderMapperXml.class);
		autoUserMapper = sqlSession.getMapper(AutoUserMapper.class);
	}


	@Test
	public void test08() {
		User user = new User();
		user.setId(3);
		user.setUsername("max");
//		user.setBirthday(new Date());
//		user.setPassword("123456");
		userMapper.addUser(user);
	}

	@Test
	public void test09(){
		User user = new User();
		user.setId(5);
		user.setUsername("jack");
		userMapper.updateUser(user);
	}

	@Test
	public void test10(){
		List<User> allUserAndOrder = userMapper.findAllUserAndOrder();
		System.out.println(allUserAndOrder);
	}

	@Test
	public void test11(){
		List<Order> all = orderMapper.findAll();
		System.out.println(all);
	}

	@Test
	public void test12(){
		List<Order> findOrderByUserId = orderMapper.findByUserId(1);
		System.out.println(findOrderByUserId);
	}

	@Test
	public void test13(){
		List<User> allUserAndRole = userMapper.findAllUserAndRole();
		allUserAndRole.forEach(System.out::println);
	}

	@Test
	public void test14(){
		PageHelper.startPage(1, 2);
		List<User> allUserAndRole = userMapper.findAllUserAndRole();
		allUserAndRole.forEach(System.out::println);
		PageInfo<User> userPageInfo = new PageInfo<User>(allUserAndRole);
		System.out.println("当前页数："+userPageInfo.getPageNum());
		System.out.println("总页数："+userPageInfo.getPages());
		System.out.println("当前页条数："+userPageInfo.getPageSize());
		System.out.println("总条数："+userPageInfo.getTotal());
	}

	@Test
	public void test15(){
		User user = new User();
		user.setId(1);
		User user1 = autoUserMapper.selectOne(user);
		System.out.println(user1);

		//2.example
		Example example = new Example(User.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("id", 1);
		List<User> users = autoUserMapper.selectByExample(example);
		System.out.println(users);
	}

}
