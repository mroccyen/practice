package com.ruyuan.twelve.juc.week09.common;

import com.ruyuan.twelve.juc.week09.AbstractTerminationThread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:工作线程
 **/
public class TerminationWorkerThread<T, V> extends AbstractTerminationThread {

    /**
     * 工作队列
     */
    private final BlockingQueue<Runnable> workQueue;

    /**
     * 执行任务的处理器
     */
    private final TaskProcessor<T, V> taskProcessor;

    public TerminationWorkerThread(BlockingQueue<Runnable> workQueue, TaskProcessor<T, V> taskProcessor) {
        this.workQueue = workQueue;
        this.taskProcessor = taskProcessor;
    }

    /**
     * 接收并行任务，并将其串行化
     *
     * @param task 任务
     * @return
     */
    public Future<V> submit(final T task) throws InterruptedException {
        Callable<V> callable = new Callable<V>() {
            @Override
            public V call() throws Exception {
                return taskProcessor.doProcessor(task);
            }
        };
        FutureTask<V> futureTask = new FutureTask<>(callable);
        workQueue.put(futureTask);

        terminationToken.reservations.incrementAndGet();

        return futureTask;
    }

    @Override
    protected void doRun() throws InterruptedException {
        // 从队列中获取执行的任务
        Runnable take = workQueue.take();
        try {
            // 执行任务的回调
            take.run();
        } finally {
            terminationToken.reservations.decrementAndGet();
        }
    }
}