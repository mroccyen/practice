package com.ruyuan.twelve.juc.week09.common;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:serial Thread confinement 串行线程封闭模式的serializer对象
 **/
public abstract class AbstractSerializer<T, V> {

    /**
     * 工作线程
     */
    private final TerminationWorkerThread<T, V> workerThread;

    public AbstractSerializer(BlockingQueue<Runnable> workQueue, TaskProcessor<T, V> taskProcessor) {
        this.workerThread = new TerminationWorkerThread<>(workQueue, taskProcessor);
    }

    /**
     * 留给子类去实现，用于根据指定参数生成相应的任务实例
     *
     * @param params 参数列表
     * @return 任务实例，用于提交给workerThread
     */
    protected abstract T makeTask(Object... params);

    /**
     * 该类对外暴露的服务方法，子类可以去定义实现比如导出excel文件
     *
     * @param params
     * @return
     */
    protected Future<V> service(Object... params) throws InterruptedException {
        T task = makeTask(params);
        return workerThread.submit(task);
    }

    /**
     * 初始化
     */
    public void init() {
        workerThread.start();
    }

    /**
     * 停止
     */
    public void shutdown() {
        workerThread.terminate();
    }
}