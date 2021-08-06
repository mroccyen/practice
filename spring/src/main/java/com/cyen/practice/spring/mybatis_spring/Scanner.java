package com.cyen.practice.spring.mybatis_spring;

import com.cyen.practice.spring.mybatis_spring.annotation.Mapper;
import com.cyen.practice.spring.mybatis_spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author qingshanpeng
 * @date 2021/8/5 16:19
 * @since 标果工厂-脱骨李
 */
@ComponentScan("com.cyen.practice.spring.mybatis_spring")
@MapperScan(basePackage = "com.cyen.practice.spring.mybatis_spring.interfaces", annotation = Mapper.class)
public class Scanner {
}
