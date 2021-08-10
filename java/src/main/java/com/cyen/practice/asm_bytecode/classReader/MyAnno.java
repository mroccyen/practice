package com.cyen.practice.asm_bytecode.classReader;

import java.lang.annotation.*;

/**
 * @author qingshanpeng
 * @date 2021/8/10 9:45
 * @since 标果工厂-托曼尼
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MyAnno {
}
