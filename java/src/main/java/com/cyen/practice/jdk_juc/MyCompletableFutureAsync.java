package com.cyen.practice.jdk_juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author qingshanpeng
 * @date 2022/6/20 14:30
 * @since 标果工厂
 */
public class MyCompletableFutureAsync {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture1 = new CompletableFuture<>();
        completableFuture1.whenCompleteAsync((t1, t2) -> {
            System.out.println("【whenCompleteAsync-1】" + t1);
        }).whenCompleteAsync((t1, t2) -> {
            System.out.println("【whenCompleteAsync-1】" + t1);
        }).thenComposeAsync(t -> {
            System.out.println("【thenComposeAsync-t-1】：" + t);
            CompletableFuture<String> c = new CompletableFuture<>();
            c.complete("3");
            try {
                System.out.println("【thenComposeAsync-c-1】：" + c.get());
            } catch (InterruptedException | ExecutionException exception) {
                exception.printStackTrace();
            }
            return c;
        }).thenApplyAsync(t -> {
            String s = t + "1";
            System.out.println("【thenApplyAsync-1】：" + s);
            return s;
        }).thenAcceptAsync(t -> {
            System.out.println("【thenAcceptAsync-1】：" + t);
        });

        CompletableFuture<String> completableFuture2 = new CompletableFuture<>();
        completableFuture2.whenCompleteAsync((t1, t2) -> {
            System.out.println("【whenCompleteAsync-2】" + t1);
        }).whenCompleteAsync((t1, t2) -> {
            System.out.println("【whenCompleteAsync-2】" + t1);
        }).thenCombineAsync(completableFuture1, (t1, t2) -> {
            String s = t1 + t2;
            System.out.println("【thenCombineAsync-2】：" + t1 + t2);
            return s;
        }).thenAcceptAsync(t -> {
            System.out.println("【thenAcceptAsync-2】：" + t);
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

        Thread.sleep(1000);
    }
}
