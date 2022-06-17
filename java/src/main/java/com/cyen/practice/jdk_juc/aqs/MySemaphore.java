package com.cyen.practice.jdk_juc.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author qingshanpeng
 * @date 2022/6/17 14:52
 * @since 标果工厂
 */
public class MySemaphore extends AbstractQueuedSynchronizer {

    int permits;

    public MySemaphore(int permits) {
        setState(permits);
        this.permits = permits;
    }

    @Override
    protected boolean tryAcquire(int arg) {
        while (true) {
            int state = getState();
            if (state < arg) {
                return false;
            }
            int i = state - arg;
            if (i < 0) {
                return false;
            }
            if (compareAndSetState(state, i)) {
                return true;
            }
        }
    }

    @Override
    protected boolean tryRelease(int arg) {
        while (true) {
            int state = getState();
            int i = state + arg;
            if (i > permits) {
                throw new IllegalArgumentException();
            }
            if (compareAndSetState(state, i)) {
                return true;
            }
        }
    }

    public void acquirePermits(int permits) {
        acquire(permits);
    }

    public void releasePermits(int permits) {
        release(permits);
    }

    public static void main(String[] args) throws InterruptedException {
        MySemaphore mySemaphore = new MySemaphore(5);

        for (int i = 0; i < 5; i++) {
            int finalI = i + 1;
            new Thread(() -> {
                mySemaphore.acquirePermits(finalI);
                System.out.println("this is " + finalI);
            }).start();
        }

        Thread.sleep(3000);
        mySemaphore.release(3);
        System.out.println("this is main");
    }
}
