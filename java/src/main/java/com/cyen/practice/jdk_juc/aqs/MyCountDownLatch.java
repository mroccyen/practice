package com.cyen.practice.jdk_juc.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author qingshanpeng
 * @date 2022/6/17 14:31
 * @since 标果工厂
 */
public class MyCountDownLatch extends AbstractQueuedSynchronizer {

    public MyCountDownLatch(int count) {
        setState(count);
    }

    @Override
    protected int tryAcquireShared(int arg) {
        return getState() == 0 ? 1 : -1;
    }

    @Override
    protected boolean tryReleaseShared(int arg) {
        while (true) {
            int state = getState();
            if (state == 0) {
                return false;
            }
            int next = state - 1;
            if (compareAndSetState(state, next)) {
                return next == 0;
            }
        }
    }

    public void await() {
        acquireShared(1);
    }

    public void countDown() {
        releaseShared(1);
    }

    public static void main(String[] args) {
        MyCountDownLatch myCountDownLatch = new MyCountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("this is " + finalI);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                myCountDownLatch.countDown();
            }).start();
        }
        myCountDownLatch.await();

        System.out.println("this is main");
    }
}
