package com.cyen.practice.nacos.client01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author qingshanpeng
 * @date 2021/09/06 11:03
 * @since 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Client01Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Client01Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Client01Application.class, args);
    }

}