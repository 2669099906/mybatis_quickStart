package com.zxr.test;

import com.zxr.dao.UserDao;
import com.zxr.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhaoxiangrui
 * @date 2020/11/12 0:06
 */
public class CacheTest {

    private UserDao userDao;
    private SqlSession sqlSession;

    @BeforeEach
    public void before() throws IOException {
        //1.加载配置文件，加载为输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.解析配置文件，创建sqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3.生产sqlSession
        sqlSession = sqlSessionFactory.openSession();//开启一个事务，默认不默认提交
        //增删改时需要手动提交
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @Test
    public void test01(){
        //第一次查询id为1的用户
        User user = userDao.findById(1);
        User user1 = new User();
        user1.setUsername("tom");
        user1.setId(2);
        userDao.updateUser(user1);
        User user2 = userDao.findById(1);
        sqlSession.commit();
        System.out.println( user == user2);
    }

}
