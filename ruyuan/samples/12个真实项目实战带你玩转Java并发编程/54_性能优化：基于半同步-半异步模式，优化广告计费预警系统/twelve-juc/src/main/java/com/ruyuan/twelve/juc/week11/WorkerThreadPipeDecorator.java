

package com.ruyuan.twelve.juc.week11;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;


/**
 * 基于工作者线程的Pipe实现类。 提交到该Pipe的任务由指定个数的工作者线程共同处理。 该类使用了两阶段终止
 *
 * @param <IN>  输入类型
 * @param <OUT> 输出类型
 * @author little
 */
public class WorkerThreadPipeDecorator<IN, OUT> implements Pipe<IN, OUT> {
    protected final BlockingQueue<IN>              workQueue;
    private final   Set<AbstractTerminationThread> workerThreads    = new HashSet<>();
    private final   TerminationToken               terminationToken = new TerminationToken();

    private final Pipe<IN, OUT> delegate;

    public WorkerThreadPipeDecorator(Pipe<IN, OUT> delegate, int workerCount) {
        this(new SynchronousQueue<IN>(), delegate, workerCount);
    }

    public WorkerThreadPipeDecorator(BlockingQueue<IN> workQueue,
                                     Pipe<IN, OUT> delegate, int workerCount) {
        if (workerCount <= 0) {
            throw new IllegalArgumentException(
                    "workerCount should be positive!");
        }

        this.workQueue = workQueue;

        this.delegate = delegate;
        for (int i = 0; i < workerCount; i++) {
            workerThreads.add(new AbstractTerminationThread(terminationToken) {
                @Override
                protected void doRun() throws Exception {
                    try {
                        // 工作线程通过两阶段终止来实现，回调执行doRun方法
                        dispatch();
                    } finally {
                        terminationToken.reservations.decrementAndGet();
                    }
                }
            });
        }

    }

    /**
     * 从工作队列中获取输入的信息，执行当前pipe的业务逻辑
     *
     * @throws InterruptedException
     */
    protected void dispatch() throws InterruptedException {
        IN input = workQueue.take();
        delegate.process(input);
    }

    @Override
    public void init(PipeContext pipeCtx) {
        delegate.init(pipeCtx);
        // 启动工作线程
        for (AbstractTerminationThread thread : workerThreads) {
            thread.start();
        }
    }

    @Override
    public void process(IN input) throws InterruptedException {
        // 输入放入到work的队列中
        workQueue.put(input);
        terminationToken.reservations.incrementAndGet();
    }

    @Override
    public void shutdown(long timeout, TimeUnit unit) {
        for (AbstractTerminationThread thread : workerThreads) {
            thread.terminate();
            try {
                thread.join(TimeUnit.MILLISECONDS.convert(timeout, unit));
            } catch (InterruptedException e) {
            }
        }
        delegate.shutdown(timeout, unit);
    }

    @Override
    public void setNextPipe(Pipe<?, ?> nextPipe) {
        delegate.setNextPipe(nextPipe);
    }

}