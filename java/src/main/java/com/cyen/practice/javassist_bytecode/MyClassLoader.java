package com.cyen.practice.javassist_bytecode;

/**
 * @author qingshanpeng
 * @date 2021/8/6 18:01
 * @since 标果工厂-脱骨李
 */
public class MyClassLoader extends ClassLoader {

	private byte[] classFile;

	public void setClassFile(byte[] classFile) {
		this.classFile = classFile;
	}

	public Class<?> defineClass(String name, byte[] classFile) {
		return defineClass(name, classFile, 0, classFile.length);
	}
}
