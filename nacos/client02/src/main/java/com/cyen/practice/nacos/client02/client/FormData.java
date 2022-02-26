package com.cyen.practice.nacos.client02.client;

import feign.form.FormProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author qingshanpeng
 * @date 2022/2/25 17:24
 * @since 标果工厂
 */
@Data
@ToString
public class FormData {
    @FormProperty("name")
    private String name;
    @FormProperty("sex")
    private String sex;
}
