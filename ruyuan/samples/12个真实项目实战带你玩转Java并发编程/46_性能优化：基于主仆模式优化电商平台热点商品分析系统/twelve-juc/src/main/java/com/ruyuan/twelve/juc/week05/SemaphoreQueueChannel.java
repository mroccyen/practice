package com.ruyuan.twelve.juc.week05;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:
 **/
public class SemaphoreQueueChannel<T> implements Channel<T> {

    /**
     * 阻塞队列
     */
    private final BlockingQueue<T> queue;

    /**
     * 流量控制
     */
    private final Semaphore semaphore;

    /**
     * 构造方法
     *
     * @param queue     阻塞队列
     * @param flowLimit 流量控制
     */
    public SemaphoreQueueChannel(BlockingQueue<T> queue, int flowLimit) {
        this.queue = queue;
        this.semaphore = new Semaphore(flowLimit);
    }

    @Override
    public T take() throws InterruptedException {
        return queue.take();
    }

    @Override
    public void put(T product) throws InterruptedException {
        semaphore.acquire();
        try {
            queue.put(product);
        } finally {
            semaphore.release();
        }
    }
}