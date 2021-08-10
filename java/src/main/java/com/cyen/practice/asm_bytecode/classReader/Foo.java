package com.cyen.practice.asm_bytecode.classReader;

/**
 * @author qingshanpeng
 * @date 2021/8/10 9:42
 * @since 标果工厂-托曼尼
 */
@MyAnno
public class Foo implements Food {
	private String name;

	public String get() {
		return "hello world";
	}

	@Override
	public void print() {
		System.out.println("this is foo...");
	}
}
