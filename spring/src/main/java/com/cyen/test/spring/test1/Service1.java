package com.cyen.test.spring.test1;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author qingsp
 * @date 2019-04-01 20:20
 */
public class Service1 implements InitializingBean, DisposableBean {
    public String getName() {
        return "zhangsan";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("开始执行InitializingBean的方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("开始执行DisposableBean的方法");
    }
}
