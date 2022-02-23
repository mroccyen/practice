package com.cyen.practice.nacos.client02.controller;

import com.cyen.practice.nacos.client02.client.Client01;
import com.cyen.practice.nacos.client02.client.UserDTO;
import com.cyen.practice.nacos.client02.client.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qingshanpeng
 * @date 2022/2/23 15:54
 * @since 标果工厂
 */
@RestController
public class TestController {

    @Autowired
    private Client01 client01;

    @GetMapping("/test")
    public String testGet() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("zhangsan");
        userDTO.setSex("1");

        UserInfo info = new UserInfo();
        info.setUserId(90L);
        info.setName("zhangsan");

        return client01.testGet(userDTO, "20", info, "1111111111111111");
    }
}
