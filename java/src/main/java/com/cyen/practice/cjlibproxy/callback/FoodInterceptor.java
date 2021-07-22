package com.cyen.practice.cjlibproxy.callback;

import com.cyen.practice.cjlibproxy.obj.Food;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author qingshanpeng
 * @date 2021/7/22 15:39
 * @since 标果工厂-苹果芒
 */
public class FoodInterceptor implements MethodInterceptor {

	private final Food food;

	public FoodInterceptor(Food food) {
		this.food = food;
	}

	@Override
	public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) {
		return food;
	}
}
