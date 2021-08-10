package com.cyen.practice.asm_bytecode.classWriter;

/**
 * @author qingshanpeng
 * @date 2021/8/10 14:40
 * @since 标果工厂-托曼尼
 */
public class Food {

	private String name = "default";

	public Food() {

	}

	public Food(String name) {

	}

	public void sub() {
		int a = 1;
		int b = 2;

		int c = b - a;

		System.out.println(c);
	}

	public String getName() {
		name = "stu";
		return name;
	}
}
