package com.zxr.plugin;

import com.zxr.dao.UserMapper;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author zhaoxiangrui
 * @date 2020/11/14 0:17
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class MyPlugin implements Interceptor {
    /**
     * 拦截方法：只要被拦截的目标对象的目标方法被执行的时候，都会执行intercept
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("对方法进行增强");
        //执行原有逻辑
        return invocation.proceed();
    }

    /**
     * 主要为了把当前的拦截器生成拦截器代理，存放到拦截器链中
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        System.out.println("存放拦截器代理到拦截器链中");
        Object wrap = Plugin.wrap(target, this);
        return wrap;
    }

    /**
     * 主要获取配置问价参数
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("获取到的配置参数：" + properties);
    }
}
