

package com.ruyuan.twelve.juc.week11;

import java.util.concurrent.TimeUnit;

/**
 * 对处理阶段的抽象。 负责对其输入进行处理，并将其输出作为下一个处理阶段的输入
 * 阶段1--》阶段2
 * 比如输入阶段01--》执行完之后阶段1的结果--》最为下一个pipe（阶段2）的输入
 *
 * @param <IN>  输入类型
 * @param <OUT> 输出类型
 * @author little
 */
public interface Pipe<IN, OUT> {

    /**
     * 设置当前Pipe实例的下一个Pipe实例。
     *
     * @param nextPipe 下一个Pipe实例
     */
    void setNextPipe(Pipe<?, ?> nextPipe);

    /**
     * 初始化当前Pipe实例对外提供的服务。
     *
     * @param pipeCtx
     */
    void init(PipeContext pipeCtx);

    /**
     * 停止当前Pipe实例对外提供的服务。
     *
     * @param timeout
     * @param unit
     */
    void shutdown(long timeout, TimeUnit unit);

    /**
     * 对输入元素进行处理，并将处理结果作为下一个Pipe实例的输入。
     * @param input 输入
     * @throws InterruptedException
     */
    void process(IN input) throws InterruptedException;
}
