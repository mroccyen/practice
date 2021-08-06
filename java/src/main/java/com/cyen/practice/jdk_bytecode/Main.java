package com.cyen.practice.jdk_bytecode;

import sun.misc.ProxyGenerator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author qingshanpeng
 * @date 2021/8/6 17:43
 * @since 标果工厂-脱骨李
 */
public class Main {
	public static void main(String[] args) {
		try {
			MyClassLoader myClassLoader = new MyClassLoader();

			User user = (User) Proxy.newProxyInstance(myClassLoader, new Class<?>[]{User.class}, new UserInvocationHandler());
			user.print();

			byte[] classFile = ProxyGenerator.generateProxyClass("Proxy1", new Class<?>[]{User.class});
			myClassLoader.setClassFile(classFile);

			Constructor<?> constructor = myClassLoader.loadClass("Proxy1").getConstructor(InvocationHandler.class);
			User proxy0 = (User) constructor.newInstance(new UserInvocationHandler());
			proxy0.print();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
