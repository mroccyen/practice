package com.cyen.practice.jdk_bigdecimal;

import java.math.BigDecimal;

/**
 * @author qingshanpeng
 * @date 2022/6/30 16:41
 * @since 标果工厂
 */
public class Test01 {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("1.233");
        BigDecimal b = new BigDecimal("2.58");
        BigDecimal add = a.add(b);
        System.out.println(add.doubleValue());
    }
}
