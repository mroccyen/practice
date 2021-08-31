package com.ruyuan.twelve.juc.week09.common;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:
 **/
public class SomeServiceTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        SomeService someService = new SomeService();
        // 初始化启动工作线程
        someService.init();

        Future<String> future = someService.doSomething("1", "消息内容");
        String result = future.get();
        System.out.println(result);
    }
}