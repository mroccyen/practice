package com.cyen.practice.jdk_proxy;

import java.lang.reflect.Proxy;

/**
 * @author qingshanpeng
 * @date 2021/8/6 14:22
 * @since 标果工厂-脱骨李
 */
public class Main {
	public static void main(String[] args) {
		SqlSession sqlSession = (SqlSession) Proxy.newProxyInstance(SqlSession.class.getClassLoader(),
				new Class<?>[]{SqlSession.class}, new SqlSessionInterceptor("default"));
		sqlSession.print();
	}
}
