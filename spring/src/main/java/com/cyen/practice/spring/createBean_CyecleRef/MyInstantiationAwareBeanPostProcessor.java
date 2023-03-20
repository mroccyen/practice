package com.cyen.practice.spring.createBean_CyecleRef;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author qingsp
 * @date 2021/5/26 14:18
 * @since 标果工厂-玫瑰香
 */
@Component
public class MyInstantiationAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {
	@Override
	public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
		if (beanName.equals("a")) {
			System.out.println("-------------a-----------------");
			((A) bean).setDesc("a");
		}
		if (beanName.equals("b")) {
			System.out.println("-------------b-----------------");
			((B) bean).setDesc("b");
		}
		return bean;
	}
}
