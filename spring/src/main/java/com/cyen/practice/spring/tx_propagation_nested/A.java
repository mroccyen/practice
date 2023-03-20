package com.cyen.practice.spring.tx_propagation_nested;

import org.springframework.beans.factory.annotation.Autowired;
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
public class A {

	@Autowired
	private B b;

	/**
	 * b.print3()为内嵌事务，抛出异常，b.print3()会回滚到保存点的位置；
	 * 外层事务没有进行异常捕获，所以会抛出异常；
	 * 然后外层事务走事务过程；
	 * 最终整个事务回滚；
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
	public void print1() throws Exception {
		System.out.println("this is a tx print1...");
		b.print1();
		b.print2();
		b.print3();
		b.print4();
	}
}
