package com.cyen.practice.jdk_proxy;

/**
 * @author qingshanpeng
 * @date 2021/8/6 14:35
 * @since 标果工厂-脱骨李
 */
public class SampleSqlSession extends SqlSessionSupport implements SqlSession {
	@Override
	public void print() {
		System.out.println("this is sampleSqlSession print...");
	}
}
