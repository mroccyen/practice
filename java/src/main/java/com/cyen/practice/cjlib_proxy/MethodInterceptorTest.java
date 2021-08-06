package com.cyen.practice.cjlib_proxy;

import com.cyen.practice.cjlib_proxy.callback.FoodInterceptor;
import com.cyen.practice.cjlib_proxy.callback.ProxyCallbackFilter;
import com.cyen.practice.cjlib_proxy.callback.StaticDispatcher;
import com.cyen.practice.cjlib_proxy.callback.TargetInterceptor;
import com.cyen.practice.cjlib_proxy.obj.Car;
import com.cyen.practice.cjlib_proxy.obj.Food;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author qingshanpeng
 * @date 2021/7/22 10:12
 * @since 标果工厂-苹果芒
 */
public class MethodInterceptorTest {
	public static void main(String[] args) {
		Car c = new Car();
		Food food = new Food();

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Car.class);
		Callback[] callbacks = new Callback[]{new TargetInterceptor(c), new StaticDispatcher(c), new FoodInterceptor(food)};
		enhancer.setCallbacks(callbacks);
		enhancer.setCallbackFilter(new ProxyCallbackFilter());

		Car car = (Car) enhancer.create();
		car.print();
	}
}
