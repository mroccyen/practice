package com.cyen.practice.cjlibproxy.callback;

import com.cyen.practice.cjlibproxy.Food;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;

/**
 * @author qingshanpeng
 * @date 2021/7/22 11:15
 * @since 标果工厂-苹果芒
 */
public class FixedValueTest {
	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Food.class);
		enhancer.setCallback((FixedValue) () -> "jiaozi");

		Food food = (Food) enhancer.create();
		System.out.println(food.getName());
	}
}
