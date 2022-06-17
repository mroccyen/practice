package com.cyen.practice.jdk_juc.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author qingshanpeng
 * @date 2022/6/17 14:00
 * @since 标果工厂
 */
public class MyReentrantLock extends AbstractQueuedSynchronizer {
    @Override
    protected boolean tryAcquire(int arg) {
        int state = getState();
        if (state == 0) {
            if (!hasQueuedPredecessors() && compareAndSetState(0, arg)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
        } else if (Thread.currentThread() == getExclusiveOwnerThread()) {
            state += arg;
            setState(state);
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        int state = getState() - arg;
        if (state == 0) {
            setState(state);
            setExclusiveOwnerThread(null);
            return true;
        }
        setState(state);
        return true;
    }

    public void lock() {
        acquire(1);
    }

    public void unlock() {
        release(1);
    }

    static MyReentrantLock sysLock = new MyReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(() -> {
                sysLock.lock();
                final int j = finalI;
                try {
                    Thread.sleep(10);
                    System.out.println("this is " + j);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                } finally {
                    sysLock.unlock();
                }
            }).start();
        }
        Thread.sleep(1000);
    }
}
