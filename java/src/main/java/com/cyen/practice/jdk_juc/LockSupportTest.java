package com.cyen.practice.jdk_juc;

import java.util.concurrent.locks.LockSupport;

/**
 * @author qingshanpeng
 * @date 2022/6/17 15:56
 * @since 标果工厂
 */
public class LockSupportTest {

    static class MyFutureTask implements Runnable {

        String result;
        volatile boolean complete = false;

        Thread thread;

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                result = "complete";
                complete = true;
                LockSupport.unpark(thread);
                System.out.println("已经完成");
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        public String get() {
            thread = Thread.currentThread();
            for (; ; ) {
                if (complete) {
                    break;
                }
                System.out.println("已经阻塞");
                LockSupport.park(this);
            }
            return result;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyFutureTask myFutureTask = new MyFutureTask();
        Thread thread = new Thread(myFutureTask);
        thread.start();
        System.out.println("结果为：" + myFutureTask.get());
    }
}
