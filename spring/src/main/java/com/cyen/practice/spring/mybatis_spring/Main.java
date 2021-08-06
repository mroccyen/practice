package com.cyen.practice.spring.mybatis_spring;

import com.cyen.practice.spring.mybatis_spring.interfaces.A;
import com.cyen.practice.spring.mybatis_spring.interfaces.D;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author qingsp
 * @date 2021/5/14 16:30
 */
public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(Scanner.class);
		context.refresh();

		D d = (D) context.getBean("d");
		d.printA();

		A a = (A) context.getBean("a");
		a.print();
	}
}
