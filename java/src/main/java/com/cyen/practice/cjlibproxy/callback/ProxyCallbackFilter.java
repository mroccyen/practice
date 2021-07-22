package com.cyen.practice.cjlibproxy.callback;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * @author qingshanpeng
 * @date 2021/7/22 15:49
 * @since 标果工厂-苹果芒
 */
public class ProxyCallbackFilter implements CallbackFilter {
	@Override
	public int accept(Method method) {
		return 2;
	}
}
