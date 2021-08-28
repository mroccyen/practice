package com.cyen.practice.jdk_stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qingshanpeng
 * @date 2021/8/28 16:03
 * @since 标果工厂-托曼尼pro
 */
public class Test1 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3");
        List<String> collect = list.stream().filter(item -> item.equals("2")).collect(Collectors.toList());
        System.out.println(collect.size());
    }
}
