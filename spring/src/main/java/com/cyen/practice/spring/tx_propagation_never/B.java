package com.cyen.practice.spring.tx_propagation_never;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author qingshanpeng
 * @date 2021/7/28 11:27
 * @since 标果工厂-脱骨李
 */
@Component
public class B {
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.NEVER, isolation = Isolation.REPEATABLE_READ)
	public String print1() throws Exception {
		System.out.println("this is b tx print1...");
		return "1";
	}
}
