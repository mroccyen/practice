package com.cyen.practice.cjlib_proxy.callback;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author qingshanpeng
 * @date 2021/7/22 15:34
 * @since 标果工厂-苹果芒
 */
public class TargetInterceptor implements MethodInterceptor {

	private final Object target;

	public TargetInterceptor(Object target) {
		this.target = target;
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("this is a car before...");
		Object invoke = proxy.invoke(target, args);
		System.out.println("this is a car after...");

		return invoke;
	}
}
