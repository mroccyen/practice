package com.cyen.practice.nacos.client02.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * @author qingshanpeng
 * @date 2022/2/23 15:58
 * @since 标果工厂
 */
@FeignClient(contextId = "client01", value = "client01")
public interface Client01 {
    @GetMapping("/test")
    String testGet(@SpringQueryMap UserDTO userDTO,
                   @RequestParam("age") String age,
                   @SessionAttribute("userInfo") UserInfo userInfo,
                   @CookieValue("userId") String userId);
}
