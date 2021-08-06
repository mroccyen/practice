package com.cyen.practice.jdk_proxy;

/**
 * @author qingshanpeng
 * @date 2021/8/6 15:34
 * @since 标果工厂-脱骨李
 */
public class SqlSessionSupport implements SqlSession {
	@Override
	public void print() {
		System.out.println("this is sqlSessionSupport print...");
	}
}
