package com.cyen.practice.spring.refresh_BeanDefinitionRegistryPostProcessor;

import com.cyen.practice.spring.aop_advice.Scanner;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author qingsp
 * @date 2021/5/14 16:30
 */
public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(Scanner.class);
		context.registerBeanDefinition("myBeanDefinitionRegistryPostProcessor", new GenericBeanDefinition() {{
			setBeanClass(MyBeanDefinitionRegistryPostProcessor.class);
		}});
		context.refresh();
	}
}
