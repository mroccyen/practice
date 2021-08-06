package com.cyen.practice.jdk_proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * todo 原理
 * 通过Proxy.newProxyInstance获取代理对象，内部通过getProxyClass0方法获取代理对象的class
 * getProxyClass0方法内部先查找缓存，没找到的话就创建，通过ProxyClassFactory工厂创建
 * ProxyClassFactory内部通过sun.misc.ProxyGenerator#generateProxyClass方法生成代理类字节码，然后通过defineClass0方法装在进虚拟机
 * 获取到代理对象的class后，再获取代理对象的构造函数
 * 最后通过构造函数的newInstance方法，并传入InvocationHandler参数得到最终的代理类
 * <p>
 * todo
 * 生成的代理类继承了Proxy，并且实现了SqlSession接口，通过构造函数传入InvocationHandler对象；
 * 代理类在实现了SqlSession的方法中，其实都是调用的InvocationHandler对象的invoke方法；
 * 代理类的字节码参考 $Proxy0.class.md
 *
 * @author qingshanpeng
 * @date 2021/8/6 14:22
 * @since 标果工厂-脱骨李
 */
public class Main {
	public static void main(String[] args) {
		SqlSession sqlSession = (SqlSession) Proxy.newProxyInstance(SqlSession.class.getClassLoader(),
				new Class<?>[]{SqlSession.class}, new SqlSessionInterceptor("default"));
		sqlSession.print();

		String path = "D:/Proxy0.class";
		byte[] classFile = ProxyGenerator.generateProxyClass("Proxy0", new Class<?>[]{SqlSession.class});

		FileOutputStream out = null;
		try {
			out = new FileOutputStream(path);
			out.write(classFile);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
