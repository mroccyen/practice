package com.cyen.practice.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author qingshanpeng
 * @date 2021/8/6 14:20
 * @since 标果工厂-脱骨李
 */
public class SqlSessionInterceptor implements InvocationHandler {

	private final String flag;

	public SqlSessionInterceptor(String flag) {
		this.flag = flag;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		SqlSession sqlSession = "default".equals(flag) ? new DefaultSqlSession() : new SampleSqlSession();

		return method.invoke(sqlSession, args);
	}
}
