package com.cyen.practice.cjlib_proxy.obj;

/**
 * @author qingshanpeng
 * @date 2021/7/22 10:34
 * @since 标果工厂-苹果芒
 */
public class Car {

	public String getColor() {
		System.out.println("default");
		return "default";
	}

	public void print() {
		System.out.println("this is a car...");
	}

	public void print1() {
		System.out.println("this is a car1...");
	}
}
