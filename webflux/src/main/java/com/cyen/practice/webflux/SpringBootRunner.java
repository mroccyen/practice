package com.cyen.practice.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * @author qingshanpeng
 * @date 2022/5/18 15:43
 * @since 标果工厂
 */
@SpringBootApplication
@EnableWebFlux
public class SpringBootRunner implements WebFluxConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRunner.class);
    }
}
