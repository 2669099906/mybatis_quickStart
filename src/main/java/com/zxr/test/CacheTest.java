package com.zxr.test;

import com.zxr.dao.UserMapper;
import com.zxr.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhaoxiangrui
 * @date 2020/11/12 0:06
 */
public class CacheTest {

    private SqlSessionFactory sqlSessionFactory;
    private UserMapper userMapper;
    private SqlSession sqlSession;

    @BeforeEach
    public void before() throws IOException {
        //1.加载配置文件，加载为输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.解析配置文件，创建sqlSessionFactory工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3.生产sqlSession
        sqlSession = sqlSessionFactory.openSession();//开启一个事务，默认不默认提交
        //增删改时需要手动提交
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void firstLevelCache(){
        //第一次查询id为1的用户
        User user = userMapper.findById(1);
        User user1 = new User();
        user1.setUsername("tom");
        user1.setId(2);
        userMapper.updateUser(user1);
        User user2 = userMapper.findById(1);
        sqlSession.commit();
        System.out.println( user == user2);
    }

    @Test
    public void secondLevelCache(){
        //获取三个sqlSession对象
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();
        //用过三个不同的sqlSession获取三个userMapper代理对象
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);

        User user1 = userMapper1.findById(1);
        //通过关闭sqlSession清除缓存
        //这里不能用clearCache()原因不明 猜测clear的时候会将而二级缓存一并清除
        sqlSession1.close();
//        User user2 = userMapper2.findById(1);
        //虽然命中了缓存但是 user1 和 user2不同的对象。二级缓存缓存的是数据而非对象引用
//        System.out.println(user1 == user2);
        User user = new User();
        user.setId(1);
        user.setUsername("max");
        userMapper3.updateUser(user);
        sqlSession3.commit();
        //当执行了增删改操作后，会清空二级缓存，导致第二次查询user(id = 1)也走了数据库
        User user2 = userMapper2.findById(1);
        System.out.println(user1 == user2);

    }

}
