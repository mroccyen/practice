package com.cyen.practice.jdk_juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author qingshanpeng
 * @date 2022/6/20 14:30
 * @since 标果工厂
 */
public class MyCompletableFutureSync {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture1 = new CompletableFuture<>();
        completableFuture1.whenComplete((t1, t2) -> {
            System.out.println("【whenComplete-1】" + t1);
        }).thenCompose(t -> {
            System.out.println("【thenCompose-t-1】：" + t);
            CompletableFuture<String> c = new CompletableFuture<>();
            c.complete("3");
            try {
                System.out.println("【thenCompose-c-1】：" + c.get());
            } catch (InterruptedException | ExecutionException exception) {
                exception.printStackTrace();
            }
            return c;
        }).thenApply(t -> {
            String s = t + "1";
            System.out.println("【thenApply-1】：" + s);
            return s;
        }).thenAccept(t -> {
            System.out.println("【thenAccept-1】：" + t);
        });

        CompletableFuture<String> completableFuture2 = new CompletableFuture<>();
        completableFuture2.whenComplete((t1, t2) -> {
            System.out.println("【whenComplete-2】" + t1);
        }).thenCombine(completableFuture1, (t1, t2) -> {
            String s = t1 + t2;
            System.out.println("【thenCombine-2】：" + t1 + t2);
            return s;
        }).thenAccept(t -> {
            System.out.println("【thenAccept-2】：" + t);
        });

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                completableFuture1.complete("1");
                completableFuture2.complete("2");
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }).start();

        System.out.println("原始结果1：" + completableFuture1.get());
        System.out.println("原始结果2：" + completableFuture2.get());
    }
}
