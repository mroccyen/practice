package com.cyen.practice.cjlib_proxy.callbackFilter;

import com.cyen.practice.cjlib_proxy.obj.Car;
import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * @author qingshanpeng
 * @date 2021/7/22 11:29
 * @since 标果工厂-苹果芒
 */
public class CallbackFilterTest {
	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Car.class);
		CallbackHelper helper = new CallbackHelper(Car.class, new Class[0]) {
			@Override
			protected Object getCallback(Method method) {
				if (method.getReturnType() == void.class) {
					return (MethodInterceptor) (obj, method1, args1, methodProxy) -> {
						System.out.println("before invocation");
						Object res = methodProxy.invokeSuper(obj, args1);
						System.out.println("after invocation");
						return res;
					};
				} else if (method.getReturnType() == String.class) {
					return (FixedValue) () -> "a hacked car";
				} else {
					return NoOp.INSTANCE;
				}
			}
		};

		enhancer.setCallbacks(helper.getCallbacks());
		enhancer.setCallbackFilter(helper);

		Car car = (Car) enhancer.create();
		car.print();
		System.out.println(car.getColor());
	}
}
