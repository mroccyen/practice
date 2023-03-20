package com.cyen.practice.spring.aop_Advisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author qingsp
 * @date 2021/6/22 13:37
 * @since 标果工厂-小白杏
 */
public class MyInterceptor implements MethodInterceptor {
	@Override
	public Object invoke( MethodInvocation invocation) throws Throwable {
		System.out.println("MyInterceptor...");
		return invocation.proceed();
	}
}
