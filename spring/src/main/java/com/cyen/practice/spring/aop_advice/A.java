package com.cyen.practice.spring.aop_advice;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Component;

/**
 * @author qingsp
 * @date 2021/6/10 14:37
 * @since 标果工厂-苹果蕉
 */
@Component
public class A {

	public String name;

	public Integer age;

	public A print() {
		System.out.println("this is a for print...");
		return this;
	}

	public A print1() {
		System.out.println("this is a for print1...");
		return this;
	}

	/**
	 * todo 外部在调用print2函数的时候，已经通过将代理对象写入了ThreadLocal中
	 * todo 这时通过AopContext.currentProxy()可以获取当前线程在ThreadLocal中代理对象，再通过代理对象调用print1函数，即可调用到切面方法
	 */
	public A print2() {
		((A) AopContext.currentProxy()).print1();
		System.out.println("this is a for print2...");
		return this;
	}

	public A print3(String name) {
		System.out.println("this is a for print3...");
		return this;
	}

	public A print4(String name, Integer age) {
		System.out.println("this is a for print4...");
		return this;
	}
}
