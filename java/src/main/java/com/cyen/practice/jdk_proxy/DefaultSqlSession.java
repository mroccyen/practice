package com.cyen.practice.jdk_proxy;

/**
 * @author qingshanpeng
 * @date 2021/8/6 14:21
 * @since 标果工厂-脱骨李
 */
public class DefaultSqlSession implements SqlSession {
	@Override
	public void print() {
		System.out.println("this is defaultSqlSession print...");
	}
}
