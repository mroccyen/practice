package com.cyen.practice.asm_bytecode.classWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author qingshanpeng
 * @date 2021/8/10 15:02
 * @since 标果工厂-托曼尼
 */
public class FoodMain {
	public static void main(String[] args) {
		String className = "com.cyen.practice.asm_bytecode.classWriter.Food";
		try {
			byte[] dump = FoodDump.dump();

			try {
				// 将二进制流写到本地磁盘上
				FileOutputStream fos = new FileOutputStream("D:/foodProxy0.class");
				fos.write(dump);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			Class<?> clazz = new MyClassLoader().defineClass(className, dump);
			Object personObj = clazz.getConstructor().newInstance();
			Method nameMethod = clazz.getDeclaredMethod("sub", null);
			nameMethod.invoke(personObj, null);

			nameMethod = clazz.getDeclaredMethod("add", null);
			nameMethod.invoke(personObj, null);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failure!");
		}
	}
}
