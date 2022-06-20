package com.cyen.practice.jdk_juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qingshanpeng
 * @date 2022/6/20 17:43
 * @since 标果工厂
 */
public class MyInheritableThreadLocal {

    static InheritableThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();
    private static ExecutorService executorService1 = Executors.newFixedThreadPool(5);
    private static ExecutorService executorService2 = Executors.newFixedThreadPool(5);
    private static AtomicInteger i = new AtomicInteger(0);

    public static void main(String[] args) {
        while (true) {
            executorService1.execute(() -> {
                if (i.get() < 10) {
                    threadLocal.set(i.getAndAdd(1));
                    executorService2.execute(() -> {
                        System.out.println(String.format("子线程名称-%s, 变量值=%s", Thread.currentThread().getName(), threadLocal.get()));
                    });
                }
            });
        }
    }
}
