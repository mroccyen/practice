package com.cyen.practice.spring.createBean_CyecleRef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author qingsp
 * @date 2021/5/26 10:49
 * @since 标果工厂-玫瑰香
 */
@Component
public class A {
	@Autowired
	B b;

	private String desc;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
