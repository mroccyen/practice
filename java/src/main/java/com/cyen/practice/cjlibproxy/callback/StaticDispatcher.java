package com.cyen.practice.cjlibproxy.callback;

import net.sf.cglib.proxy.Dispatcher;

/**
 * @author qingshanpeng
 * @date 2021/7/22 15:37
 * @since 标果工厂-苹果芒
 */
public class StaticDispatcher implements Dispatcher {

	private final Object target;

	public StaticDispatcher(Object target) {
		this.target = target;
	}

	@Override
	public Object loadObject() {
		System.out.println("this is a car static...");
		return this.target;
	}
}
