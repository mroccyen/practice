package com.cyen.practice.cjlibproxy.callback;

import com.cyen.practice.cjlibproxy.Car;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @author qingshanpeng
 * @date 2021/7/22 10:12
 * @since 标果工厂-苹果芒
 */
public class MethodInterceptorTest {
	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Car.class);
		enhancer.setCallback((MethodInterceptor) (obj, method, args1, proxy) -> {
			System.out.println("this is a car before...");
			Object invoke = proxy.invokeSuper(obj, args1);
			System.out.println("this is a car after...");

			return invoke;
		});

		Car car = (Car) enhancer.create();
		car.print();
	}
}
