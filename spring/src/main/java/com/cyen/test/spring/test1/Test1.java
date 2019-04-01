package com.cyen.test.spring.test1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author qingsp
 * @date 2019-04-01 20:16
 */
public class Test1 {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("services.xml");

        Service1 service = context.getBean("service1", Service1.class);
        String name = service.getName();
        System.out.println(name);

        ((ClassPathXmlApplicationContext) context).close();
    }
}
