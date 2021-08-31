package com.ruyuan.twelve.juc.week05;

import java.util.concurrent.BlockingQueue;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:基于arrayBlockingQueue实现的阻塞队列的通道，所有附件通过阻塞队列的方式来上传和下载
 **/
public class BlockingQueueChannel<T> implements Channel<T> {

    /**
     * 通道阻塞队列
     */
    private final BlockingQueue<T> queue;

    public BlockingQueueChannel(BlockingQueue<T> queue) {
        this.queue = queue;
    }

    @Override
    public T take() throws InterruptedException {
        return queue.take();
    }

    @Override
    public void put(T product) throws InterruptedException {
        queue.put(product);
    }
}