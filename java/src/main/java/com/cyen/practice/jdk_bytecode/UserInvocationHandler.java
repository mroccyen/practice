package com.cyen.practice.jdk_bytecode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author qingshanpeng
 * @date 2021/8/6 17:42
 * @since 标果工厂-脱骨李
 */
public class UserInvocationHandler implements InvocationHandler {
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		User user = new UserImpl();
		return method.invoke(user, args);
	}
}
