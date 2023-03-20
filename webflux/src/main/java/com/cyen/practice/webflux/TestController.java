package com.cyen.practice.webflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author qingshanpeng
 * @date 2022/5/18 16:02
 * @since 标果工厂
 */
@RestController
public class TestController {
    @GetMapping("/find/name")
    public Mono<String> getName(@RequestParam String name) {
        return Mono.just(name);
    }
}
