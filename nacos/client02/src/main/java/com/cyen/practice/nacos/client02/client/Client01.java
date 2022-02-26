package com.cyen.practice.nacos.client02.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
                   @CookieValue("userId") String userId);

//    @PostMapping(value = "/form-data", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    String testPostFormData(@RequestPartMap FormData formData);

//    @PostMapping(value = "/form-data", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    String testPostFormData(@RequestPart("name") String name, @RequestPart("sex") String sex);

    @PostMapping(value = "/form-data", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String testPostFormData(FormData formData);
}
