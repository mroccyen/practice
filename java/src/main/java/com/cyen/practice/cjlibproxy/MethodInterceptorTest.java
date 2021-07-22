package com.cyen.practice.cjlibproxy;

import com.cyen.practice.cjlibproxy.callback.FoodInterceptor;
import com.cyen.practice.cjlibproxy.callback.ProxyCallbackFilter;
import com.cyen.practice.cjlibproxy.callback.StaticDispatcher;
import com.cyen.practice.cjlibproxy.callback.TargetInterceptor;
import com.cyen.practice.cjlibproxy.obj.Car;
import com.cyen.practice.cjlibproxy.obj.Food;
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
