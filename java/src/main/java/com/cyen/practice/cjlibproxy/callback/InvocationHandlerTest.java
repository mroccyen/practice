package com.cyen.practice.cjlibproxy.callback;

import com.cyen.practice.cjlibproxy.Car;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

/**
 * @author qingshanpeng
 * @date 2021/7/22 11:02
 * @since 标果工厂-苹果芒
 */
public class InvocationHandlerTest {
	public static void main(String[] args) {
		Car c = new Car();

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Car.class);
		enhancer.setCallback((InvocationHandler) (proxy, method, args1) -> {
			System.out.println("this is a car before...");
			return method.invoke(c, args1);
		});

		Car car = (Car) enhancer.create();
		car.print();
	}
}
