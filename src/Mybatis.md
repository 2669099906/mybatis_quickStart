# Mybatis

#### jdbc已经可以对数据库进行增删改查操作为什么后期还会出现mybatis等持久层框架？

###### JDBC问题分析

1.数据库配置信息硬编码问题。

解决方法：配置文件

2.频繁创建释放数据库连接（建立TCP连接 耗费资源）

解决方法：配置连接池 dbcp c3p0 durid



1.sql语句、设置参数、获取结果参数均存在硬编码问题

解决方法：配置文件



1.手动封装返回结果集，较为繁琐

解决方法：使用反射技术



数据库配置和sql语句的配置文件需要分开配置。因为数据库配置轻易不会发生改变，而sql配置频繁发生变动



# 自定义持久层框架思路

**使用端：使用自定义持久层框架的一方**

为了解决硬编码问题，应该提供两部分配置信息

- 数据库配置信息（sqlMapConfig.xml文件存放数据库配置信息）
- SQL配置信息（mapper.xml文件存放sql配置信息）：sql语句、参数类型、返回值类型

**自定义持久层框架本身**

持久层框架本质就是对JDBC代码进行封装。

实现自定义持久层框架的简单设计步骤

1. 加载配置文件
   - 根据配置文件路径，加载文件字节输入流，存储在内存中
   - 创建Resource类 方法 InputStream getResourceAsStream(String path)
2. 创建javaBean
   - 存放配置文件解析出来的内容
   - Configuration :核心配置类，存放sqlMapConfig.xml解析出来的内容
   - MappedStatement:映射配置类，存放mapper.xml解析出来的内容
3. 解析配置文件
   - 创建SqlSessionFactoryBuilder类， 方法build(InputStream in)
   - 使用dom4j对xml文件进行解析,将解析内容封装到JavaBean中
   - 创建SqlSessionFactory对象，用于生产SqlSession会话对象
4. 创建SqlSessionFactory接口以及实现类DefaultSqlSessionFactory
   - openSession(): 生产SqlSession
5. 创建SqlSession接口和实现类DefaultSession
   - 定义对数据库的crud操作
   - selectList
   - selectOne
   - update
   - delete
6. 创建Executor接口和SimpleExecutor实现类
   - query(Configuration, MppedStatement, Object... params) 执行jdbc代码















# MyBatis缓存

缓存有读取快，减少数据库操作的作用

mybatis存在一级缓存和二级缓存

一级缓存是SqlSession级别， 底层是HashMap集合

二级缓存是namespace(mapper)级别



##### 一级缓存

一级缓存 cacheKey的组成：statementId,params,boundSql,rowBounds

![1605111705368](C:\Users\86155\Desktop\学习课件\第一阶段\模块一\images\1605111705368.png)

```java
		User user = userDao.findById(1);
//去一级缓存中查询，存在直接返回，不存在，查询数据库，同时将查询出来的结果存放到一级缓存中
        User user1 = new User();
        user1.setUsername("tom");
        user1.setId(2);
        userDao.updateUser(user1);
		sqlSession.commit();
//做增删改操作，并提交事务，就清空了一级缓存 sqlSession.clearCache(),可以手动清空缓存
//保证缓存中的数据是最新的数据，避免脏读
        User user2 = userDao.findById(1);
//由于更新操作刷新了一级缓存，所以还是需要查询数据库，获取结果值
        System.out.println( user == user2);
//此时两个user结果不是同一个引用
```

一级缓存实际上是一个HashMap

```java
private Map<Object, Object> cache = new HashMap<Object, Object>();
```

cacheKey何时被创建

```java
@Override
  public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) throws SQLException {
    BoundSql boundSql = ms.getBoundSql(parameter);
    CacheKey key = createCacheKey(ms, parameter, rowBounds, boundSql);
    return query(ms, parameter, rowBounds, resultHandler, key, boundSql);
 }

  @SuppressWarnings("unchecked")
  @Override
  public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, CacheKey key, BoundSql boundSql) throws SQLException {
    ErrorContext.instance().resource(ms.getResource()).activity("executing a query").object(ms.getId());
    if (closed) {
      throw new ExecutorException("Executor was closed.");
    }
    if (queryStack == 0 && ms.isFlushCacheRequired()) {
      clearLocalCache();
    }
    List<E> list;
    try {
      queryStack++;
      list = resultHandler == null ? (List<E>) localCache.getObject(key) : null;
      if (list != null) {
        handleLocallyCachedOutputParameters(ms, key, parameter, boundSql);
      } else {
        list = queryFromDatabase(ms, parameter, rowBounds, resultHandler, key, boundSql);
      }
    } finally {
      queryStack--;
    }
    if (queryStack == 0) {
      for (DeferredLoad deferredLoad : deferredLoads) {
        deferredLoad.load();
      }
      // issue #601
      deferredLoads.clear();
      if (configuration.getLocalCacheScope() == LocalCacheScope.STATEMENT) {
        // issue #482
        clearLocalCache();
      }
    }
    return list;
  }
```



```java
  CacheKey createCacheKey(MappedStatement ms,
                          Object parameterObject,
                          RowBounds rowBounds,
                          BoundSql boundSql);
```

创建key的内容

```java
	CacheKey cacheKey = new CacheKey();
	//statementId
    cacheKey.update(ms.getId());
	//两个分页相关参数
    cacheKey.update(rowBounds.getOffset());
    cacheKey.update(rowBounds.getLimit());
	//要执行的sql语句
    cacheKey.update(boundSql.getSql());
```

