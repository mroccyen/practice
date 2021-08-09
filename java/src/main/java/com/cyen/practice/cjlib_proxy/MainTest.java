package com.cyen.practice.cjlib_proxy;

import com.cyen.practice.cjlib_proxy.obj.Foo;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

/**
 * @author qingshanpeng
 * @date 2021/7/22 11:02
 * @since 标果工厂-苹果芒
 */
public class MainTest {
	public static void main(String[] args) {
		Foo foo = new Foo();

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Foo.class);
		enhancer.setCallback((InvocationHandler) (proxy, method, args1) -> {
			System.out.println("this is a car before...");
			return method.invoke(foo, args1);
		});

		Foo f = (Foo) enhancer.create();
		System.out.println(f.getName());
	}
}
