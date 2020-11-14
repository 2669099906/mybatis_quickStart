package com.zxr.test;

import com.zxr.dao.UserMapper;
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
 * @author zhaoxiangrui
 * @date 2020/11/14 12:10
 */
public class MybatisAnalysisTest {

    /**
     *
     * 传统方式
     */
    @Test
    public void test1() throws IOException {
        //1.读取配置文件，堆区字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.解析配置文件输入流，封装Configuration对象，创建DefaultSqlSession对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3.生产了DefaultSqlSession实例对象 并且设置事务非自动提交 完成executor对象的创建
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.1根据statementId从Configuration对象的map集合中获取MappedStatement对象
        //4.2将查询操作委派给executor执行器
        //statement:namespace.id
        List<User> user = sqlSession.selectList("com.zxr.dao.UserMapper.findById", 1);
        System.out.println(user);
    }

    /**
     * mapper代理方式
     */
    @Test
    public void test02() throws IOException {
        //1.读取配置文件，堆区字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.解析配置文件输入流，封装Configuration对象，创建DefaultSqlSession对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3.生产了DefaultSqlSession实例对象 并且设置事务非自动提交 完成executor对象的创建
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.获取到动态代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.findById(1);
        System.out.println(user);

    }
}
