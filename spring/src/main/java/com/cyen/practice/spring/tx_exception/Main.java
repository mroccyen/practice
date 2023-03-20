package com.cyen.practice.spring.tx_exception;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author qingsp
 * @date 2021/5/14 16:30
 */
public class Main {
	public static void main(String[] args) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(Scanner.class);
		context.refresh();

//		A1 a1 = (A1) context.getBean("a1");
//		a1.print1();

//		B1 b1 = (B1) context.getBean("b1");
//		b1.print1();
	}
}
