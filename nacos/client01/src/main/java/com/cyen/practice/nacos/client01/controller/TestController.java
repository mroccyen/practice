package com.cyen.practice.nacos.client01.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author qingshanpeng
 * @date 2022/2/23 15:54
 * @since 标果工厂
 */
@Slf4j
@RestController
public class TestController {
    @GetMapping("/test")
    public String testGet(HttpServletRequest request,
                          @RequestParam String name,
                          @RequestParam String sex,
                          @RequestParam String age) {
        Object userInfo = request.getHeader("userInfo");
        log.info("" + userInfo);
        Object userId = request.getHeader("Cookie");
        log.info("" + userId);
        HttpSession session = request.getSession();
        Object userId1 = session.getAttribute("userId");
        log.info("" + userId1);
        return "userId=" + userId + ",userInfo1=" + userInfo + ",userInfo2=" + name + sex + age;
    }

    @PostMapping("/form-data")
    public String testPostFormData(FormData formData) {
        return formData.toString();
    }
}
